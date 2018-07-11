/**
 *
 * 项目名：  steping
 * 文件名：  Item11_Clone.java
 * 模块说明：
 * 修改历史：
 * 2018-03-05 - Songyanyan - 创建。
 */
package methodsCommonToObjects;

/**
 * 关于Clone的方方面面
 *
 * @author Songyanyan
 */
public class Item11_Clone {
  
}

// 1.实现Cloneable接口
// 2.重写clone方法
// 3.Object类的clone()一个native方法，native方法的效率一般来说都是远高于java中的非native方法
class BaseCloneClass implements Cloneable {
  public int a;
  
  public Object clone() {
    BaseCloneClass obj = null;
    try {
      obj = (BaseCloneClass) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return obj;
  }
}

class ShadowClone {
  class NormalClassA {
    private int i;
    
    public NormalClassA(int i) {
      this.i = i;
    }
    
    public void doubleValue() {
      this.i = i * 2;
    }
  }
  
  class CloneClassB {
    public int a;
    public NormalClassA normalClassA = new NormalClassA(1);
    
    @Override
    public CloneClassB clone() {
      try {
        return (CloneClassB) super.clone();
      } catch (CloneNotSupportedException e) {
        throw new AssertionError(); // Can't happen
      }
    }
  }
  
}

// 非基本数据类型，需要进行深度克隆
class DeepClone {
  class HashTable implements Cloneable {
    private Entry[] buckets = {};
    
    private class Entry {
      final Object key;
      Object value;
      Entry next;
      
      Entry(Object key, Object value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
      }
      
      // 链表过长容易造成栈溢出
      Entry deepCopy() {
        return new Entry(key, value, next == null ? null : next.deepCopy());
      }
      
      // 可以用迭代代替递归
      Entry deepCopy_2() {
        Entry result = new Entry(key, value, next);
        for (Entry p = result; p.next != null; p = p.next) {
          p.next = new Entry(p.next.key, p.next.value, p.next.next);
        }
        return result;
      }
    }
    
    // 私有HashTable Entry被加强，支持深度拷贝
    @Override
    public HashTable clone() {
      try {
        HashTable result = (HashTable) super.clone();
        result.buckets = new Entry[buckets.length];
        for (int i = 0; i < buckets.length; i++) {
          if (buckets[i] != null)
            result.buckets[i] = buckets[i].deepCopy();
        }
        return result;
      } catch (CloneNotSupportedException e) {
        throw new AssertionError();
      }
    }
  }
}

// 3.拷贝工厂，类似于“拷贝构造器”的静态工厂
// 为了继承而设计的类型不应该实现这个接口，其它接口也都不应该扩展

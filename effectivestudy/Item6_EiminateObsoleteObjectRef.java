
/**
 * 项目名：  syy-prj
 * 文件名：  Item6_EiminateObsoleteObjectRef.java
 * 模块说明：
 * 修改历史：
 * 2018-02-26 - Songyanyan - 创建。
 */

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 消除过期对象引用
 *
 * @author Songyanyan
 */
public class Item6_EiminateObsoleteObjectRef {
  class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    public Stack() {
      elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    
    public void push(Object obj) {
      ensureCapacity();
      elements[size++] = obj;
    }
    
    // 栈内部维护着过期引用
    public Object pop_bad() {
      if (size == 0)
        throw new EmptyStackException();
      return elements[--size];
    }
    
    public Object pop_nice() {
      if (size == 0)
        throw new EmptyStackException();
      Object new_Element = elements[--size];
      elements = null;// eliminate obsolete obj
      return new_Element;
    }
    
    private void ensureCapacity() {
      if (elements.length == size) {
        elements = Arrays.copyOf(elements, 2 * size + 1);
      }
    }
  }
}

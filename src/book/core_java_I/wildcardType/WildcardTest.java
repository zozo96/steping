/**
 * 项目名：  steping
 * 文件名：  WildcardTest.java
 * 模块说明：
 * 修改历史：
 * 2018-08-28 - Songyanyan - 创建。
 */
package wildcardType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 泛型测试类
 *
 * @author Songyanyan
 */
public class WildcardTest {
  public static void main(String[] args) {
    String[] words = { "YY", "HAVE", "A", "LITTLE", "LAMB" };
    Pair<String> mm = ArrayAlg.minmax(words);
    
    // Pair<String>[] pair0 = new Pair<String>[10]; // ERROR
    Pair<String>[] pair = (Pair<String>[]) new Pair<?>[10];// CORRECT
    Collection<Pair<String>> collection = new ArrayList<>();
    ArrayAlg.addAll(collection, mm);
    
    Pair<Integer> nn = new Pair(4, 5);
    if (mm.getClass() == nn.getClass()) {
      System.out.println("yes");
    }
    
    System.out.println(mm.getMin());
    System.out.println(mm.getMax());
    
    System.out.println(ArrayAlg.<Integer> getMiddle(1, 2, 3));
    System.out.println(ArrayAlg.getMiddle(1.0, 2, 3));
    System.out.println(ArrayAlg.getMiddle("hello", 2, 3));
    System.out.println(ArrayAlg.getMiddle("hello", 2, null));
    
    Pair<String> pair1 = Pair.makePair(String::new);
    
    String[] strings = Pair.minmax(String[]::new, "Hello", "How", "Are", "You");
  }
}

class ArrayAlg {
  
  /**
   * @param a
   *          字符串数组
   * @return 数组中最小和最大值
   */
  public static Pair<String> minmax(String[] a) {
    if (a == null || a.length == 0) {
      return null;
    }
    String min = a[0];
    String max = a[0];
    
    for (String word : a) {
      if (min.compareTo(word) > 0)
        min = word;
      if (max.compareTo(word) < 0)
        max = word;
    }
    
    return new Pair<>(min, max);
  }
  
  public static <T> T getMiddle(T... a) {
    return a[a.length / 2];
  }
  
  public static <T extends Comparable> T min(T[] a) {
    if (a == null || a.length == 0)
      return null;
    T smallest = a[0];
    for (T num : a) {
      if (smallest.compareTo(num) > 0)
        smallest = num;
    }
    
    return smallest;
  }
  
  @SafeVarargs
  @SuppressWarnings("unchecked")
  public static <T> void addAll(Collection<T> collection, T... ts) {
    for (T t : ts) {
      collection.add(t);
    }
  }
  
}

class Interval<T extends Comparable & Serializable> implements Serializable {
  private T lower;
  private T upper;
  
  public Interval(T first, T second) {
    if (first.compareTo(second) <= 0) {
      lower = first;
      upper = second;
    } else {
      lower = second;
      upper = first;
    }
  }
}

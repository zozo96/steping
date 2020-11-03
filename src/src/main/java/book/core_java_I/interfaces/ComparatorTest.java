/**
 * 项目名：  steping
 * 文件名：  Comparator.java
 * 模块说明：
 * 修改历史：
 * 2018-07-17 - Songyanyan - 创建。
 */
package book.core_java_I.interfaces;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 *
 * @author Songyanyan
 */
public class ComparatorTest {
  public static void main(String[] args) {
    Comparator<String> comparator = new LengthComparator();
    System.out.println(comparator.compare("hello", "world"));
    System.out.println("hello".compareTo("world"));

    String[] names = { "songyanyan", "songjunfeng", "syy", "jeff" };
    Arrays.sort(names, comparator);
    System.out.println(names);
  }

}

class LengthComparator implements Comparator<String> {
  @Override
  public int compare(String o1, String o2) {
    return o1.length() - o2.length();
  }
}
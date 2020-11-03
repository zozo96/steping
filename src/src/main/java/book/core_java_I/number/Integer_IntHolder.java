/**
 * 项目名：  steping
 * 文件名：  Integer_IntHolder.java
 * 模块说明：
 * 修改历史：
 * 2018-07-16 - Songyanyan - 创建。
 */
package book.core_java_I.number;

import org.omg.CORBA.IntHolder;

/**
 * 整型的自动装箱拆箱
 *
 * @author Songyanyan
 */
public class Integer_IntHolder {
  public static void main(String[] args) {
    Integer a = 1000;
    Integer b = 1000;
    System.out.println(a == b);

    Integer c = 100;
    Integer d = 100;
    System.out.println(c == d);

    Integer n = 1;
    Double x = 2.0;
    System.out.println(true ? n : x);

    IntHolder y = new IntHolder();
    y.value = 3;
    changeInt(y);
    System.out.println(y.value);
  }

  public static void changeInt(IntHolder x) {
    x.value = x.value * 3;
  }
}

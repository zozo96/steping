/**
 * 项目名：  steping
 * 文件名：  MutiParams.java
 * 模块说明：
 * 修改历史：
 * 2018-07-16 - Songyanyan - 创建。
 */
package book.core_java_I.parameter;

/**
 * 多参数方法
 *
 * @author Songyanyan
 */
public class MutiParams {
  public static void main(String[] args) {
    int n = 1;
    System.out.printf("%d %s", n, "widgets");
    double[] values = { 1.2, 123123.12, -0.999, 12312 };
    System.out.println("\n" + max(values));
    System.out.println(max(1.2, 123123.12, -0.999, 12312));
  }

  public static double max(double... values) {
    double largest = Double.NEGATIVE_INFINITY;
    for (double val : values)
      if (val > largest)
        largest = val;
    return largest;
  }
}

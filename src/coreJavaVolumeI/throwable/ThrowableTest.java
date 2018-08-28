/**
 * 项目名：  steping
 * 文件名：  ThrowableTest.java
 * 模块说明：
 * 修改历史：
 * 2018-07-24 - Songyanyan - 创建。
 */
package throwable;

import java.io.IOException;
import java.util.Scanner;

/**
 * 异常测试类
 *
 * @author Songyanyan
 */
public class ThrowableTest extends IOException {
  // 所有异常都是由Throwable继承而来，分为两类：Error和Exception（RuntimeExcp-程序错误和IOExcp-其他异常）
  // 派生于 Error 类或 RuntimeException 类的所有异常称为非受查( unchecked ) 异常，其他的异常称为受查（ checked) 异常
  // 编译器将核查是否为所有的受査异常提供了异常处理器
  
  public ThrowableTest() {
  }
  
  public ThrowableTest(String gripe) {
    super(gripe);
  }
  
  public static int factorial(int n) {
    System.out.println("factorial(" + n + "):");
    Throwable t = new Throwable();
    StackTraceElement[] frames = t.getStackTrace();
    for (StackTraceElement f : frames)
      System.out.println(f);
    int r;
    if (n <= 1)
      r = 1;
    else
      r = n * factorial(n - 1);
    System.out.println("return " + r);
    return r;
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Enter n: ");
    int n = in.nextInt();
    factorial(n);
  }
}

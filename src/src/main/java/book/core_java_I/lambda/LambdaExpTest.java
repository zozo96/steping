/**
 * 项目名：  steping
 * 文件名：  LambdaExpTest.java
 * 模块说明：
 * 修改历史：
 * 2018-07-17 - Songyanyan - 创建。
 */
package book.core_java_I.lambda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.IntConsumer;

/**
 * lambda表达式
 *
 * @author Songyanyan
 */
public class LambdaExpTest {
  public static void main(String[] args) {
    // 比较器
    Comparator<String> comparator = (first, second) -> {
      return first.length() - second.length();
    };
    String first = "Hello";
    // Comparator<String> stringComparator = (first, second) -> first.length() - second.length();

    // 事件监听
    ActionListener listener = e -> System.out.println("the time is " + new Date());
    Timer timer = new Timer(1000, e -> System.out.println());
    Timer timer1 = new Timer(1000, System.out::println);

    List<String> list = new ArrayList();
    list.removeIf(integer -> integer == null);
    list.sort(String::compareToIgnoreCase);

    repeatMessage("Hello", 1000);
    repeat(10, i -> System.out.println(i));
  }

  public static void repeatMessage(String text, int delay) {
    ActionListener listener = event -> {
      System.out.println(text);
      Toolkit.getDefaultToolkit().beep();
    };
    new Timer(delay, listener).start();
  }

  public void numDown(int num, int delay) {
    for (int i = 0; i <= num; i++) {
      ActionListener listener = event -> {
        System.out.println(this);
        // System.out.println(num--); // Error: 不能引用可变的变量值num
        // System.out.println(i); // Error: 不可以调用可变变量i
      };
      new Timer(delay, listener).start();
    }
  }

  public static void repeat(int n, IntConsumer consumer) {
    for (int i = 0; i < n; i++)
      consumer.accept(i);
  }
}

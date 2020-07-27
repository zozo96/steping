/**
 * 项目名：  steping
 * 文件名：  CallBackTest.java
 * 模块说明：
 * 修改历史：
 * 2018-07-17 - Songyanyan - 创建。
 */
package book.core_java_I.interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * 回调函数方法测试
 *
 * @author Songyanyan
 */
public class CallBackTest {

  public static void main(String[] args) {
    ActionListener listener = new TimePrinter();
    Timer t = new Timer(1000, listener);
    t.start();

    JOptionPane.showMessageDialog(null, "Quit?");
    System.exit(0);
  }
}

class TimePrinter implements ActionListener {
  public void actionPerformed(ActionEvent event) {
    System.out.println("At the tone, the time is " + new Date());
    Toolkit.getDefaultToolkit().beep(); // 响铃
  }
}

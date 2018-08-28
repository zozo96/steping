/**
 * 项目名：  steping
 * 文件名：  LoggerTest.java
 * 模块说明：
 * 修改历史：
 * 2018-07-26 - Songyanyan - 创建。
 */
package log;

import java.util.logging.Logger;

/**
 * 日志记录器 与包类似，日志记录器也具有层次结构 对于父日志记录器设置了日志级别，它的子记录器也会继承这个级别。
 * 
 * @author Songyanyan
 */
public class LoggerTest {
  public final Logger logger = Logger.getLogger(this.getClass().getName());
  
  public static void main(String[] args) {
    
  }
  
  public void read(String a, String b) {
    
  }
  
}

/**
 * 项目名：  steping
 * 文件名：  CountAndSay.java
 * 模块说明：
 * 修改历史：
 * 2018-11-06 - Songyanyan - 创建。
 */
package leetCode.basic;

/**
 * LC.38.迭代
 *
 * @author Songyanyan
 */
public class CountAndSay {
  public void countAndSay(int n) {
    StringBuilder result = new StringBuilder();
    result.append(1);
    
    for (int i = 2; i <= n; i++) {
      result = say(result);
      System.out.println("第" + i + "个字符串(L:" + result.length() + ")");
    }
  }
  
  public StringBuilder say(StringBuilder s) {
    StringBuilder result = new StringBuilder();
    int count = 0;
    char c = '0';
    for (int i = 0; i < s.length(); i++) {
      c = s.charAt(i);
      if (count != 0) {
        if (s.charAt(i - 1) != c) {
          result = result.append(count).append(s.charAt(i - 1));
          count = 0;
        }
      }
      count++;
    }
    result = result.append(count).append(c);
    
    return result;
  }
}

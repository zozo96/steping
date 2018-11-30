/**
 * 项目名：  steping
 * 文件名：  Test.java
 * 模块说明：
 * 修改历史：
 * 2018-10-11 - Songyanyan - 创建。
 */
package games;

import java.util.Scanner;

/**
 *
 *
 * @author Songyanyan
 */
public class Test {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[][] box = new int[3][3];
    for (int i = 0; i < box.length; i++) {
      for (int j = 0; j < box[i].length; j++) {
        box[i][j] = sc.nextInt();
      }
    }
    
    Klotski klotski = new Klotski(box);
    klotski.getResult();
  }
}

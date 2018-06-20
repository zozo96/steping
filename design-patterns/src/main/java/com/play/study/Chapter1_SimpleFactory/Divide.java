/**
 * 项目名：  steping
 * 文件名：  Divide.java
 * 模块说明：
 * 修改历史：
 * 2018-06-20 - Songyanyan - 创建。
 */
package com.play.study.Chapter1_SimpleFactory;

/**
 * 相除
 *
 * @author Songyanyan
 */
public class Divide extends Calculator {
  public double getResult() {
    try {
      if (numB == 0)
        throw new Exception("除数不能为0");
    } catch (Exception e) {
      e.printStackTrace();
    }
    double result = numA / numB;
    return result;
  }
}

/**
 * 项目名：  steping
 * 文件名：  Multiply.java
 * 模块说明：
 * 修改历史：
 * 2018-06-20 - Songyanyan - 创建。
 */
package com.play.study.Chapter1_SimpleFactory;

/**
 * 乘法
 *
 * @author Songyanyan
 */
public class Multiply extends Calculator {
  public double getResult() {
    double result = numA * numB;
    return result;
  }
}

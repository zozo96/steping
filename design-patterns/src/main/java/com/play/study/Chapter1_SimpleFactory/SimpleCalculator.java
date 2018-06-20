/**
 * 项目名：  steping
 * 文件名：  SimpleCalculator.java
 * 模块说明：
 * 修改历史：
 * 2018-06-20 - Songyanyan - 创建。
 */
package com.play.study.Chapter1_SimpleFactory;

/**
 * 简易计算器
 *
 * @author Songyanyan
 */
public class SimpleCalculator {
  public static void main(String[] args) {
    try {
      Calculator calculator = OperatorFactory.createOperate("+");
      calculator.numA = 3;
      calculator.numB = 2;
      double result = calculator.getResult();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}

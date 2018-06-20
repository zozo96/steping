/**
 * 项目名：  steping
 * 文件名：  OperatorFactory.java
 * 模块说明：
 * 修改历史：
 * 2018-06-20 - Songyanyan - 创建。
 */
package com.play.study.Chapter1_SimpleFactory;

/**
 * 操作符简单工厂
 * 
 * 考虑实例化谁 考虑用一个单独的类来做这个创造实例的过程
 *
 * 声明父类 利用多态执行getResult
 *
 * @author Songyanyan
 */
public class OperatorFactory {
  public static Calculator createOperate(String operator) {
    Calculator calculator = null;
    switch (operator) {
      case "+":
        calculator = new Add();
        break;
      case "-":
        calculator = new Cut();
        break;
      case "*":
        calculator = new Multiply();
        break;
      case "/":
        calculator = new Divide();
        break;
      default:
        break;
    }
    return calculator;
  }
}

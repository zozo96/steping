/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：  steping
 * 文件名：  OperationEntry.java
 * 模块说明：
 * 修改历史：
 * 2018-08-15 - Songyanyan - 创建。
 */
package leetCode.basic;

/**
 * main入口
 *
 * @author Songyanyan
 */
public class OperationEntry {
  public static void main(String[] args) {
    Operation_Add add = new Operation_Add();
    System.out.println(add.doAdd(15, 27));
    System.out.println(add.doCut(27, 15));
  }
}

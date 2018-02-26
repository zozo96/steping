/**
 * 项目名：  syy-prj
 * 文件名：  Item4_Noninstantiability.java
 * 模块说明：
 * 修改历史：
 * 2018-02-24 - Songyanyan - 创建。
 */

/**
 * 不可实例化
 *
 * @author Songyanyan
 */
public class Item4_Noninstantiability {
  private Item4_Noninstantiability(){
    throw new AssertionError();
  }
  //...
}

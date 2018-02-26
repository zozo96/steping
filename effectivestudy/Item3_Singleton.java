/**
 * 项目名：  syy-prj
 * 文件名：  Item3_Singleton.java
 * 模块说明：
 * 修改历史：
 * 2018-02-23 - Songyanyan - 创建。
 */

/**
 * Singleton的实现方式
 *
 * @author Songyanyan
 */
public class Item3_Singleton {

}

// Singleton with public static final field
class Elvis_Public{
  public static final Elvis_Public Instance = new Elvis_Public();
  private Elvis_Public(){}
  //...
}
// Singleton with static factory
class Elvis_Factory{
  private static final Elvis_Factory Instance = new Elvis_Factory();
  public static Elvis_Factory getInstance(){
    return Instance;
  }
  private Elvis_Factory(){}
}
// Singleton with single_element enum
enum Elvis_Enum{
  Instance;
  //...
}

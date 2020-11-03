package java8.ActionParamLize_Apple;
/**
 * 项目名：  steping
 * 文件名：  ActionParamLize_Apple.AppleColorPredicate.java
 * 模块说明：
 * 修改历史：
 * 2018-04-04 - Songyanyan - 创建。
 */

/**
 * 颜色的筛选
 *
 * @author Songyanyan
 */
public class AppleColorPredicate implements ApplePredicator {
  @Override
  public boolean test(Apple apple) {
    return apple.getColor() == "red";
  }
}

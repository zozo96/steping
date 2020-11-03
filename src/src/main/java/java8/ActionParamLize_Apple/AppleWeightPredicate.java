package java8.ActionParamLize_Apple;
/**
 * 项目名：  steping
 * 文件名：  ActionParamLize_Apple.AppleWeightPredicate.java
 * 模块说明：
 * 修改历史：
 * 2018-04-04 - Songyanyan - 创建。
 */

/**
 * 重量的筛选
 *
 * @author Songyanyan
 */
public class AppleWeightPredicate implements ApplePredicator {

  @Override
  public boolean test(Apple apple) {
    return apple.getWeight() > 10;
  }
}

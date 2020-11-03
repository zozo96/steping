/**
 * 项目名：  steping
 * 文件名：  SalaryTitles.java
 * 模块说明：
 * 修改历史：
 * 2018-12-13 - Songyanyan - 创建。
 */
package utils.file.read;

/**
 *
 *
 * @author Songyanyan
 */
public enum SalaryTitles implements EnumBase {

  actuallyReceived("实发合计"), accumulationFund("公积金"), accumulationFundPlus("补充公积金"), sandCard(
    "餐贴"), sandCard2("餐补——杉德卡"), tax("个人所得税"), tax2("个调税");

  // 一定要放在枚举类型下方
  private String name;

  private SalaryTitles(String title) {
    this.name = title;
  }

  @Override
  public String getName() {
    return this.name;
  }
}

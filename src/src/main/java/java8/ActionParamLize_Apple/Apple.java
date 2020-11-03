package java8.ActionParamLize_Apple;
/**
 * 项目名：  steping
 * 文件名：  ActionParamLize_Apple.Apple.java
 * 模块说明：
 * 修改历史：
 * 2018-04-04 - Songyanyan - 创建。
 */

/**
 * 苹果类
 *
 * @author Songyanyan
 */
public class Apple {
  private Integer weight;
  private String color;
  private String kind;

  public Apple(Integer weight, String color) {
    this.weight = weight;
    this.color = color;
  }

  public Apple(Integer weight) {
    this.weight = weight;
  }

  public Apple(Integer weight, String color, String kind) {
    this.weight = weight;
    this.color = color;
    this.kind = kind;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }
}

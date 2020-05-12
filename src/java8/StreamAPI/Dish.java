/**
 * 项目名：  steping
 * 文件名：  Dish.java
 * 模块说明：
 * 修改历史：
 * 2018-04-10 - Songyanyan - 创建。
 */
package StreamAPI;

/**
 * 菜类
 *
 * @author Songyanyan
 */
public class Dish {
  private final String name;
  private final boolean vegetarian;
  private final int calories;
  private final Type type;
  
  public Dish(String name, boolean vegetarian, int calories, Type type) {
    this.name = name;
    this.vegetarian = vegetarian;
    this.calories = calories;
    this.type = type;
  }
  
  public String getName() {
    return name;
  }
  
  public boolean isVegetarian() {
    return vegetarian;
  }
  
  public int getCalories() {
    return calories;
  }
  
  public Type getType() {
    return type;
  }
  
  public enum Type {
    MEAT, FISH, OTHER
  }
  
}

/**
 * 项目名：  syy-prj
 * 文件名：  Item2_Builder.java
 * 模块说明：
 * 修改历史：
 * 2018-02-05 - Songyanyan - 创建。
 */


/**
 * 构造器
 *
 * @author Songyanyan
 */
public class Item2_Builder {

  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;

  public static class Builder{
    // required
    private final int servingSize;
    private final int servings;
    // optional
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public Builder ( int servingSize, int servings){
      this.servings = servings ;
      this.servingSize = servingSize;
    }
    public Builder fat(int val){
      this.fat = fat;
      return this;
    }
    public Builder sodium(int val){
      this.sodium = sodium;
      return this;
    }
    public Builder calories(int val){
      this.calories = calories;
      return this;
    }
    public Builder carbohydrate(int val){
      this.carbohydrate = carbohydrate;
      return this;
    }

    public Item2_Builder build(){
      return new Item2_Builder(this);
    }
  }

  private Item2_Builder(Builder builder){
    calories = builder.calories;
    servingSize = builder.servingSize;
    servings = builder.servings;
    fat = builder.fat;
    sodium = builder.sodium;
    carbohydrate = builder.carbohydrate;
  }

  public static void main(String[] args) {
    Item2_Builder cocaCola = new Item2_Builder.Builder(100,10).calories(100).fat(50).sodium(60)
      .build();
  }
}

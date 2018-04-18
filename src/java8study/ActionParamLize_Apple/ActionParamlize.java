package ActionParamLize_Apple;
/**
 * 项目名：  steping
 * 文件名：  ActionParamLize_Apple.ActionParamlize.java
 * 模块说明：
 * 修改历史：
 * 2018-04-04 - Songyanyan - 创建。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 行为参数化
 *
 * @author Songyanyan
 */
public class ActionParamlize {
  public static void main(String[] args) {
    List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"),
      new Apple(120, "red"));
    
    List<Apple> heavyApples = filterApple(inventory, new AppleWeightPredicate());
    int i = 5;
    ++i;
    int j = i++;
    
    int a = 0;
    a += (a -= ((++a) * (a--)));
    ;
    System.out.println(a);
  }
  
  public static List<Apple> filterApple(List<Apple> list, ApplePredicator p) {
    List<Apple> result = new ArrayList<Apple>();
    for (Apple apple : list) {
      if (p.test(apple))
        result.add(apple);
    }
    return result;
  }
}

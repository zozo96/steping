/**
 * 项目名：  steping
 * 文件名：  Recombination.java
 * 模块说明：
 * 修改历史：
 * 2018-04-09 - Songyanyan - 创建。
 */
package java8.ActionParamLize_Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 复合用法
 *
 * @author Songyanyan
 */
public class Recombination {
  public static void main(String[] args) {
    // 1.比较器Comparator复合 thenComparing() reversed()
    List<Apple> inventory = Arrays.asList(new Apple(10, "red"), new Apple(8, "green"));
    // 默认递增
    inventory.sort(Comparator.comparing(Apple::getWeight));
    inventory.sort(Comparator.comparing(Apple::getWeight).reversed());// 重量递减排序
    // 比较器链
    inventory.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor));

    // 2.谓词Predicate复合 negate() or() and()
    // 优先级从左至右顺序 a.or(b).and(c)可以看作(a||b)&&c
    Predicate<Apple> redApple = apple -> apple.getColor() == "red";
    Predicate<Apple> notRedApple = redApple.negate();
    Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);
    Predicate<Apple> redAndHeavyAppleOrNotRed = redAndHeavyApple.or(notRedApple);

    // 3.函数复合 f(g(x))=f.andThen(g); g(f(x))=f.compose(g);
    Function<Integer, Integer> f = x -> x + 1;
    Function<Integer, Integer> g = x -> x * 2;
    Function<Integer, Integer> h = f.andThen(g);
    Function<Integer, Integer> i = f.compose(g);
  }
}

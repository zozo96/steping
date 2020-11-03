/**
 * 项目名：  steping
 * 文件名：  MethodReferences.java
 * 模块说明：
 * 修改历史：
 * 2018-04-09 - Songyanyan - 创建。
 */
package java8.ActionParamLize_Apple;

import java8.utils.TriFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 方法引用
 *
 * @author Songyanyan
 */
public class MethodReferences {
  public static void main(String[] args) {
    // 方法引用 根据苹果getWeight方法进行比较
    List<Apple> inventory = Arrays.asList(new Apple(10, "red"), new Apple(8, "green"));
    // 默认递增
    inventory.sort(Comparator.comparing(Apple::getWeight));

    // 方法引用：根据字母排序
    List<String> list = Arrays.asList("a", "b", "d", "c");
    list.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
    list.sort(String::compareToIgnoreCase);

    // 构造器引用：单参数构造 IntegerToApple 三种写法
    Function<Integer, Apple> integerAppleFunction0 = (Integer weight) -> new Apple(weight);
    Function<Integer, Apple> integerAppleFunction1 = (weight) -> new Apple(weight);
    Function<Integer, Apple> integerAppleFunction = Apple::new;
    Apple apple = integerAppleFunction.apply(10);

    // 构造器引用：单参数 IntegersToApples
    List<Integer> weights = Arrays.asList(4, 5, 7, 9);
    List<Apple> apples = map(weights, Apple::new);

    // IntegerStringToApple
    BiFunction<Integer, String, Apple> integerStringAppleBiFunction0 = (weight, color) -> new Apple(
      weight, color);
    BiFunction<Integer, String, Apple> integerStringAppleBiFunction = Apple::new;
    Apple apple1 = integerStringAppleBiFunction.apply(10, "red");

    // 构造器引用：多参数
    TriFunction<Integer, String, String, Apple> multiAppleTriFunction = Apple::new;
    Apple apple2 = multiAppleTriFunction.apply(20, "green", "红富士");
  }

  public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
    List<R> result = new ArrayList<>();
    for (T t : list) {
      result.add(function.apply(t));
    }
    return result;
  }
}

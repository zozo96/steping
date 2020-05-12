
/**
 * 项目名：  steping
 * 文件名：  CollectorsFunc.java
 * 模块说明：
 * 修改历史：
 * 2018-04-18 - Songyanyan - 创建。
 */

import StreamAPI.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static StreamAPI.Dish.Type.FISH;
import static StreamAPI.Dish.Type.MEAT;
import static StreamAPI.Dish.Type.OTHER;
import static java.util.stream.Collectors.reducing;

/**
 * 收集器用法
 *
 * @author Songyanyan
 */
public class CollectorsFunc {
  public static void main(String[] args) {
    List<Dish> dishes = Arrays.asList(
      new Dish("pork", false, 800, MEAT),
      new Dish("beef", false, 700, MEAT),
      new Dish("chicken", false, 400, MEAT),
      new Dish("french fries", false, 530, OTHER),
      new Dish("rice", false, 350, OTHER),
      new Dish("pizza", false, 550, OTHER),
      new Dish("salmon", false, 450, FISH));
    
    long dishNum = dishes.stream().count();
    long dishNum1 = dishes.stream().collect(Collectors.counting());
    
    // 卡路里最高的菜
    Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
    Optional<Dish> mostCalories = dishes.stream().collect(Collectors.maxBy(dishCaloriesComparator));
    
    // 汇总 Collectors.summingInt .averagingInt .averagingLong .averagingDouble
    int totalCalories = dishes.stream().collect(Collectors.summingInt(Dish::getCalories));
    double averagingCalories = dishes.stream().collect(Collectors.averagingInt(Dish::getCalories));
    
    // summarizingInt工厂方法 返回 IntSummaryStatics 包含count sum min max average信息 还有Double和Long的
    IntSummaryStatistics dishSummaryStatics = dishes.stream().collect(Collectors.summarizingInt(
      Dish::getCalories));
    dishSummaryStatics.getAverage();
    dishSummaryStatics.getCount();
    dishSummaryStatics.getMax();
    dishSummaryStatics.getMin();
    
    // joining连接字符串
    String disherMenu = dishes.stream().map(Dish::getName).collect(Collectors.joining(","));
    System.out.println(disherMenu);
    
    // reducing广义的归约汇总 计算卡路里
    int totalCalories1 = dishes.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
    // reducing计算最大卡路里值
    Optional<Dish> mostCaloriesDish = dishes.stream().collect(reducing(
      (dish1, dish2) -> dish1.getCalories() > dish2.getCalories() ? dish1 : dish2));
    
    // collect和reduce
    // 语义问题：reduce方法旨在把两个值结合成一个新值，是不可变的归约，collect方法是要改变容器，从而累计要输出的结果
    // collect方法适合表达可变容器上的归约，适合并行操作
    // 对reduce的滥用，不能并行工作，多个线程并发修改同一个数据结构可能会破坏List本身
    Stream<Integer> integerStream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 11).stream();
    List<Integer> integerList = integerStream.reduce(new ArrayList<Integer>(),
      (List<Integer> list, Integer e) -> {
        list.add(e);
        return list;
      },
      (List<Integer> list1, List<Integer> list2) -> {
        list1.addAll(list2);
        return list1;
      });
    
  }
}

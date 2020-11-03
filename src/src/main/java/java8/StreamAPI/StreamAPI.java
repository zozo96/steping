package java8.StreamAPI;
/**
 * 项目名：  steping
 * 文件名：  StreamAPI.StreamAPI.java
 * 模块说明：
 * 修改历史：
 * 2018-04-10 - Songyanyan - 创建。
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java8.StreamAPI.Dish.Type.*;

/**
 * Stream流的用法
 * <li>流和集合区别：</li>
 * <li>1.流只能被消费一次（按需构建），集合是存在于内存中的（提前构建）2.</li>
 * <li>2.遍历数据方式：Collection接口需要外部迭代（用户做）Stream库使用内部迭代</li>
 *
 * <li>流的使用：数据源+中间操作链(eg: filter map limit sorted distinct)+终端操作(eg: count forEach collect)</li>
 * <li></li>
 *
 * <li>模式：筛选、切片、查找、匹配、映射、归约</li>
 *
 * @author Songyanyan
 */
public class StreamAPI {
  public static void main(String[] args) {
//    StreamAPI api = new StreamAPI();
//    api.objAfterStream();
      int a = 2;
      switch (a) {
          case 0:
              System.out.println("0");
          case 1:
              System.out.println("1");
          case 2:
              System.out.println("2");
              break;
          case 3:
              System.out.println("3");
          default:
              System.out.println("de");
      }
  }

  private void objAfterStream(){
      List<SimpleObj> objs = new ArrayList<>();
      objs.add(new SimpleObj(1, "Sonya"));
      objs.add(new SimpleObj(2, "Any"));
      List<SimpleObj> newObjs = objs.stream().filter(simpleObj -> simpleObj.getAge() == 1).collect(Collectors.toList());
      newObjs.get(0).setName("oldSonya");
      System.out.println(objs.get(0).getName());
      System.out.println(objs.get(1).getName());
  }

  private void test(){
      List<Dish> dishes = Arrays.asList(
              new Dish("pork", false, 800, MEAT),
              new Dish("beef", false, 700, MEAT),
              new Dish("chicken", false, 400, MEAT),
              new Dish("french fries", false, 530, OTHER),
              new Dish("rice", false, 350, OTHER),
              new Dish("pizza", false, 550, OTHER),
              new Dish("salmon", false, 450, FISH));

      // 取卡路里>300的前三个菜名 filter map limit 均返回一个流
      List<String> thirdHighCaloricDishNames = dishes.stream().filter(dish -> dish
              .getCalories() > 300)
              .map(Dish::getName).limit(3).collect(Collectors.toList());
      System.out.println(thirdHighCaloricDishNames);

      // 流只能被遍历一次，遍历后就会被消费掉
      Stream<String> stringStream = dishes.stream().map(dish -> dish.getName());
      stringStream.forEach(System.out::println);
      // 第二次消费流时会报错java.lang.IllegalStateException: stream has already been operated upon or closed
      // stringStream.forEach(System.out::println);

      // 三种迭代方式
      // 集合的foreach循环外部迭代 比背后的Iterator丑陋的多
      List<String> names = new ArrayList<>();
      for (Dish d : dishes) {
          names.add(d.getName());
      }
      // 集合用迭代器做外部迭代
      List<String> names1 = new ArrayList<>();
      Iterator<Dish> iterator = dishes.iterator();
      while (iterator.hasNext()) {
          Dish d = iterator.next();
          names1.add(d.getName());
      }
      // 内部迭代
      List<String> names2 = dishes.stream().map(Dish::getName).collect(Collectors.toList());

      // 查看流的内部流转 1.limit 短路 2.filter和map操作合并了--循环合并
      List<String> thirdNames = dishes.stream().filter(dish -> {
          System.out.println("filtering : " + dish.getName());
          return dish.getCalories() > 300;
      })
              .map(dish -> {
                  System.out.println("map : " + dish.getName());
                  return dish.getName();
              }).limit(3)
              .collect(Collectors.toList());
      System.out.println(thirdNames);

      // 筛选和切片 谓词筛选 distinct去重 limit(n)截断前n，skip(n)扔掉前n个元素流中不足n个返回空流
      List<Dish> vegetarianMenu = dishes.stream().filter(Dish::isVegetarian).collect(Collectors
              .toList());
      List<Integer> nums = Arrays.asList(1, 2, 3, 4, 6, 5, 6);
      nums.stream().filter(integer -> integer % 2 == 0).distinct().forEach(System.out::println);
      nums.stream().limit(3).forEach(System.out::println);
      nums.stream().skip(3).forEach(System.out::println);

      // map映射
      List<String> stringList = Arrays.asList("Java8", "Lambda", "In", "Action");
      List<Integer> stringLength = stringList.stream().map(String::length).collect(Collectors
              .toList());
      List<Integer> menuLength = dishes.stream().map(Dish::getName).map(String::length).collect(
              Collectors.toList());
      String[] words = { "stream", "of", "words" };
      // 接受一个数组并产生一个流
      Stream<String> streamOfWords = Arrays.stream(words);
      // 生成的是一个List<Stream<String>> 流的列表（先拆分成字母数组，又对每个数组变成流）distinct无效
      List<Stream<String>> stringList1 = stringList.stream().map(word -> word.split("")).map(
              Arrays::stream).distinct().collect(Collectors.toList());
      // flatMap 如上例，各个数组在flatMap映射后不是分别映射成一个流，而是映射成流的内容，扁平化为一个流
      List<String> stringList2 = stringList.stream().map(word -> word.split("")).flatMap(
              Arrays::stream).distinct().collect(Collectors.toList());

      // 映射练习：数组中的数做平方
      List<Integer> numberSquare = nums.stream().map(n -> n * n).collect(Collectors.toList());
      // 映射练习：两个数组，输出数对
      List<Integer> nums1 = Arrays.asList(1, 2, 3);
      List<int[]> pairs = nums.stream().flatMap(i -> nums1.stream().map(j -> new int[] { i, j }))
              .collect(
                      Collectors.toList());
      // 映射练习：返回总和能被3整除的数对
      List<int[]> pairs1 = nums.stream().flatMap(i -> nums1.stream().filter(j -> (i + j) % 3 == 0)
              .map(j -> new int[] { i, j })).collect(
              Collectors.toList());

      // 匹配(boolean) allMatch anyMatch noneMatch
      if (nums.stream().anyMatch(integer -> integer.equals(3))) {
          System.out.println();
      }
      // 查找元素(Optional<T>是一个容器类 代表一个值存在或不存在) findAny findFirst
      Optional<Integer> first = nums.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
      Optional<Integer> any = nums.stream().map(x -> x * x).filter(x -> x % 3 == 0).findAny();

      // 归约 reduce()有初始值、无初始值 用法：元素求和、最大值、最小值
      // 有初始值:元素求和
      int sumNum = nums.stream().reduce(0, (a, b) -> a + b);
      int sumNum1 = nums.stream().reduce(0, Integer::sum);
      // 无初始值:元素求和 若流为空sumNum2=0
      Optional<Integer> sumNum2 = nums.stream().reduce(Integer::sum);
      // 最大值 若流为空 maxNum=0
      Optional<Integer> maxNum = nums.stream().reduce(Integer::max);
      // 为了区分 最大值=0还是流为空
      OptionalInt optionalIntMax = dishes.stream().mapToInt(Dish::getCalories).max();
      optionalIntMax.orElse(1);// 没有最大值默认一个最大值
      // 最小值
      Optional<Integer> minNum = nums.stream().reduce(Integer::min);

      // 数值流IntStream LongStream DoubleStream 避免例如Integer::sum暗含的自动装箱成本
      // mapToInt 返回一个IntStream 就可以调用IntStream接口中的sum方法
      int calories = dishes.stream().mapToInt(Dish::getCalories).sum();
      // 转换回对象流
      IntStream intStream = dishes.stream().mapToInt(Dish::getCalories);
      Stream<Integer> integerStream = intStream.boxed();
      // Optional原始类型特化版本 OptionalInt OptionalLong OptionalDouble
      OptionalInt optionalIntMaxCalory = dishes.stream().mapToInt(Dish::getCalories).max();
      optionalIntMaxCalory.orElse(9999);// 没有最大值默认一个最大值

      // 数值范围 IntStream LongStream的静态方法 range[start,end) rangeClosed[start,end]
      IntStream intStream1 = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0);
      IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0).forEach(System.out::println);
      IntStream.range(1, 100).filter(i -> i % 2 == 0).forEach(System.out::println);

      // 数值流练习：勾股数流
      Stream<int[]> triples = IntStream.rangeClosed(0, 100).boxed().flatMap(a -> IntStream
              .rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).mapToObj(b -> new int[] {
                      a, b, (int) Math.sqrt(a * a + b * b)
              }));
      // 优化：只做一遍平方根计算
      Stream<int[]> triples2 = IntStream.rangeClosed(0, 100).boxed().flatMap(a -> IntStream
              .rangeClosed(a, 100).mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }).filter(
                      t -> t[2] % 1 == 0));

      // 由值创建流
      Stream<String> stream = Stream.of("Java8", "Lambdas", "In", "Action");
      stream.map(String::toUpperCase).forEach(System.out::println);
      // 空流
      Stream<String> stream1 = Stream.empty();
      // 由数组创建流
      int[] numArray = { 1, 23, 4, 18 };
      int sumAll = Arrays.stream(numArray).sum();
      // 由文件生成流 处理文件等I/O的NIO API（非阻断I/O）

      // 创建无限流
      // 1.迭代
      Stream.iterate(0, n -> n + 2).limit(10);

      // 对HaspMap进行流化
      HashMap<Integer, Integer> map = new HashMap<>();
      map.entrySet().stream().sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue())))
              .map(o -> o.getKey()).collect(Collectors.toList());
  }

    class SimpleObj{
        private Integer age;
        private String name;

        public SimpleObj(int age, String name){
            this.age = age;
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

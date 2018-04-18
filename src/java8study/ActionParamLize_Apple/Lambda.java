package ActionParamLize_Apple;
/**
 * 项目名：  steping
 * 文件名：  ActionParamLize_Apple.Lambda.java
 * 模块说明：
 * 修改历史：
 * 2018-04-04 - Songyanyan - 创建。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static utils.Filter.filter;

/**
 * Lambda表达式用法
 *
 * @author Songyanyan
 */
public class Lambda {
  public static void main(String[] args) {
    
    List<Apple> inventory = Arrays.asList(new Apple(10, "red"), new Apple(8, "green"));
    List<Integer> nums = Arrays.asList(1, 5, 4);
    // 筛选条件
    List<Apple> redApples = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
    List<Integer> evenNums = filter(nums, (Integer i) -> i % 2 == 1);
    
    // 用Comparator来排序：根据weight升序排序
    inventory.sort(new Comparator<Apple>() {
      @Override
      public int compare(Apple o1, Apple o2) {
        Integer a = new Integer(1);
        a.compareTo(2);
        return o1.getWeight().compareTo(o2.getWeight());
      }
    });
    // 用Comparator来比较对象 compareTo
    inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
    
    // 用Runnable执行代码块
    Thread t = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Hello World");
      }
    });
    // 用Runnable执行代码块:Lambda用法
    Thread thread = new Thread(() -> System.out.println("Hello World"));
    
    // 使用Lambda表达式赋值 run签名()
    Runnable r1 = () -> System.out.println("Hello World 1");
    Runnable r2 = new Runnable() {
      @Override
      public void run() {
        System.out.println("Hello World 2");
      }
    };
    process(r1);
    process(r2);
    // 使用Lambda表达式作为参数
    process(() -> System.out.println("Hello World 3"));
    
    // Predicate 使用,boolean test()，涉及表达式类型为boolean可以用 nonEmptyStringPredicate可以作为过滤条件
    Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
    
    // Consumer 使用,void accept(),对元素执行动作可以用这个，消费一个对象
    printForEach(Arrays.asList(1, 2, 3, 4), (Integer i) -> System.out.println(i));
    
    // Function 使用，T apply(R),对象转换
    List<Integer> list = doMap(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
    
    // Supplier 创建一个对象
    Supplier<Apple> appleSupplier = () -> new Apple(10, "red");
    
  }
  
  // java.lang.Runnable
  public static void process(Runnable r) {
    r.run();
  }
  
  // java.util.function.Consumer<T>
  public static <T> void printForEach(List<T> list, Consumer<T> c) {
    for (T l : list) {
      c.accept(l);
    }
  }
  
  // Function<T,R>
  public static <T, R> List<R> doMap(List<T> list, Function<T, R> f) {
    List<R> result = new ArrayList<R>();
    for (T s : list) {
      result.add(f.apply(s));
    }
    return result;
  }
}

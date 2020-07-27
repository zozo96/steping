/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：  steping
 * 文件名：  Pair.java
 * 模块说明：
 * 修改历史：
 * 2018-08-28 - Songyanyan - 创建。
 */
package book.core_java_I.wildcardType;

import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * 泛型类
 *
 * @author Songyanyan
 */
public class Pair<T> {
  private T min;
  private T max;

  public Pair() {
    min = null;
    max = null;
  }

  public Pair(T min, T max) {
    this.min = min;
    this.max = max;
  }

  public static <T> Pair<T> makePair(Supplier<T> constr) {
    return new Pair<>(constr.get(), constr.get());
  }

  public static <T> Pair<T> makePair(Class<T> tClass) throws InstantiationException,
    IllegalAccessException {
    return new Pair<>(tClass.newInstance(), tClass.newInstance());
  }

  public static <T extends Comparable> T[] minmax(IntFunction<T[]> constr, T... a) {
    // T[] mm = (T[]) new Object[2]; // Compiles with warning
    T[] mm = constr.apply(2); // minmax 方法使用这个参数生成一个有正确类型的数组：
    mm[0] = a[0];
    mm[1] = a[0];
    for (T word : a) {
      if (mm[0].compareTo(word) > 0)
        mm[0] = word;
      if (mm[1].compareTo(word) < 0)
        mm[1] = word;
    }
    return mm;
  }

  public T getMin() {
    return min;
  }

  public void setMin(T min) {
    this.min = min;
  }

  public T getMax() {
    return max;
  }

  public void setMax(T max) {
    this.max = max;
  }
}

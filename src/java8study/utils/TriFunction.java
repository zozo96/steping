/**
 * 项目名：  steping
 * 文件名：  TriFunction.java
 * 模块说明：
 * 修改历史：
 * 2018-04-09 - Songyanyan - 创建。
 */
package utils;

/**
 * 三个参数构造器引用
 *
 * @author Songyanyan
 */
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
  R apply(T t, U u, V v);
}

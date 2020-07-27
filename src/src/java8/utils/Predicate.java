/**
 * 项目名：  steping
 * 文件名：  Predicate.java
 * 模块说明：
 * 修改历史：
 * 2018-04-04 - Songyanyan - 创建。
 */
package java8.utils;

/**
 * 行为谓词接口
 *
 * @author Songyanyan
 */
@FunctionalInterface
public interface Predicate<T> {
  boolean test(T t);
}

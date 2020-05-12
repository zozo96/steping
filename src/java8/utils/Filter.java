/**
 * 项目名：  steping
 * 文件名：  Filter.java
 * 模块说明：
 * 修改历史：
 * 2018-04-04 - Songyanyan - 创建。
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 行为筛选
 *
 * @author Songyanyan
 */
public class Filter {
  public static <T> List<T> filter(List<T> list, Predicate<T> p) {
    List<T> result = new ArrayList<>();
    for (T e : list) {
      if (p.test(e))
        result.add(e);
    }
    return result;
  }
}

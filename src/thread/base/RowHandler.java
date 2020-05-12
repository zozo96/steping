package base;

import java.sql.ResultSet;

/**
 * 该接口只负责从数据库中查询出来结果集进行操作，最终返回什么数据结构，需要自己处理
 *
 * @author Sophi
 * @since 2019/5/25
 **/
public interface RowHandler<T> {
    T handle(ResultSet set);
}

package base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 记录查询实现策略模式
 * @see #query 仅仅只是做了数据查询，真正对数据最后的封装在于如何实现 Handler 的 handler 方法，对于不同的 Hanlder 就可以实现返回不同的结构体。
 *
 * @author Sophi
 * @since 2019/5/24
 **/
public class RecordQuery {
    private final Connection connection;
    public RecordQuery(Connection connection){
        this.connection = connection;
    }

    public <T> T query(RowHandler<T> handler, String sql, Object... params) throws Exception{
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            int index = 1;
            for (Object obj : params){
                stmt.setObject(index++, obj);
            }

            ResultSet set = stmt.executeQuery();
            // 调用 RowHandler
            return handler.handle(set);
        }
    }
}

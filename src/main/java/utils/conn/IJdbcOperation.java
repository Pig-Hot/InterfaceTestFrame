package utils.conn;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuran on 2018/11/22 0022
 */
public interface IJdbcOperation {

    List<Map<String, Object>> query(String sql, Object... params);

    List<Map<String, Object>> query(String sql, List<Object> params);

    long queryLong(String sql, Object... params);

    boolean insert(String sql, Object... params);

    boolean update(String sql, Object... params);

    boolean delete(String sql, Object... params);

}

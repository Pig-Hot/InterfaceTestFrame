package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuran on 2018/11/13 0013
 * JDBC操作数据库工具类
 */
public class JDBCUtils {
    private String user = null;
    private String password = null;
    private String url = null;
    private Connection conn = null;
    private static final Object lock = new Object();
    private static final Logger logger = LoggerFactory.getLogger(JDBCUtils.class);

    private static JDBCUtils db;

    private JDBCUtils(String user, String password, String url) {
        this.user = user;
        this.password = password;
        this.url = url;
    }

    public static JDBCUtils getInstance(String user, String pwd, String url) {
        if (db == null) {
            synchronized (lock) {
                db = new JDBCUtils(user, pwd, url);
            }
        }
        return db;
    }

    private Connection getConn() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Map<String, Object>> query(String sql, Object... params) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            //获得连接
            conn = getConn();
            assert conn != null;
            pst = conn.prepareStatement(sql);
            int paramsIndex = 1;
            for (Object p : params) {
                pst.setObject(paramsIndex++, p);
            }
            rs = pst.executeQuery();
            ResultSetMetaData rst = rs.getMetaData();
            int column = rst.getColumnCount();
            List<Map<String, Object>> rstList = new ArrayList<Map<String, Object>>();
            while (rs.next()) {
                Map<String, Object> m = new HashMap<String, Object>();
                for (int i = 1; i <= column; i++) {
                    m.put(rst.getColumnName(i), rs.getObject(i));
                }
                rstList.add(m);
            }
            return rstList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(rs, pst, conn);
        }
    }

    public List<Map<String, Object>> query(String sql, List<Object> params) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            assert conn != null;
            pst = conn.prepareStatement(sql);
            int paramsIndex = 1;
            for (Object p : params) {
                pst.setObject(paramsIndex++, p);
            }
            rs = pst.executeQuery();
            ResultSetMetaData rst = rs.getMetaData();
            int column = rst.getColumnCount();
            List<Map<String, Object>> rstList = new ArrayList<Map<String, Object>>();
            while (rs.next()) {
                Map<String, Object> m = new HashMap<String, Object>();
                for (int i = 1; i <= column; i++) {
                    m.put(rst.getColumnName(i), rs.getObject(i));
                }
                rstList.add(m);
            }
            return rstList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(rs, pst, conn);
        }
    }

    public long queryLong(String sql, Object... params) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            assert conn != null;
            pst = conn.prepareStatement(sql);
            int paramsIndex = 1;
            for (Object p : params) {
                pst.setObject(paramsIndex++, p);
            }
            rs = pst.executeQuery();
            rs.last();
            return rs.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean insert(String sql, Object... params) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            assert conn != null;
            pst = conn.prepareStatement(sql);
            int paramsIndex = 1;
            for (Object p : params) {
                pst.setObject(paramsIndex++, p);
            }
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(null, pst, conn);
        }
    }

    public boolean update(String sql, Object... params) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            assert conn != null;
            pst = conn.prepareStatement(sql);
            int paramsIndex = 1;
            for (Object p : params) {
                pst.setObject(paramsIndex++, p);
            }
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(null, pst, conn);
        }
    }

    public boolean delete(String sql, Object... params) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            assert conn != null;
            pst = conn.prepareStatement(sql);
            int paramsIndex = 1;
            for (Object p : params) {
                pst.setObject(paramsIndex++, p);
            }
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(null, pst, conn);
        }
    }

    private static void close(ResultSet rs, PreparedStatement pst, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pst = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
}

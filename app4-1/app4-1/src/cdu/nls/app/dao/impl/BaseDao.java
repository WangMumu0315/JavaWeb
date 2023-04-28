package cdu.nls.app.dao.impl;

import java.sql.*;

public class BaseDao {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/abc?serverTimezone=GMT";
    String jdbcUser = "root";
    String jdbcPwd = "root";
    protected Connection conn = null;
    protected Statement stmt = null;
    protected PreparedStatement pstmt = null;
    protected ResultSet rs = null;

    public BaseDao() {
        connect();
    }

    protected void connect() {
        try {
            // 1 加载驱动程序
            Class.forName(driver).newInstance();
            // 2 创建数据库连接
            conn = DriverManager.getConnection(url, jdbcUser, jdbcPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void close() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}

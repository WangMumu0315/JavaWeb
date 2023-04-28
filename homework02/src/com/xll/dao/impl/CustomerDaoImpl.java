package com.xll.dao.impl;

import com.xll.dao.BaseDao;
import com.xll.dao.UserDao;
import com.xll.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public class CustomerDaoImpl extends BaseDao implements UserDao {


    @Override
    public User find(String name, String password) {
        User user = null;
        String sql = "SELECT * FROM user_table WHERE name=? AND password=?";
        System.out.println("DAO验证用户check(user) : " + sql);

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setCreateTime(rs.getLong("createTime"));
                user.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("DAO验证用户出错：" + sql + "," + e.getMessage());
        }

        return user;
    }

    @Override
    public User findById(int id) {
        User condition = new User();
        condition.setId(id);
        List<User> userList = query(condition);
        if (userList != null && userList.size() == 1) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public List<User> query(int start, int num) {
        return query(null, start, num);
    }

    @Override
    public List<User> query(User condition) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user_table";
        if (condition != null) {
            sql += " WHERE 1=1";
            if (condition.getId() != 0) {
                sql += " AND id='" + condition.getId() + "'";
            }
            if (condition.getName() != null && !condition.getName().isEmpty()) {
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
        }
        sql += " ORDER BY id DESC";

        System.out.println("DAO查询find(condition) : " + sql);

        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setCreateTime(rs.getLong("createTime"));
                user.setLastAccessTime(rs.getLong("lastAccessTime"));
                user.setStatus(rs.getString("status"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询用户出错：" + sql + "," + e.getMessage());
        }

        return userList;
    }

    @Override
    public List<User> query(User condition, int start, int num) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user_table";
        if (condition != null) {
            sql += " WHERE 1=1";
            if (condition.getId() != 0) {
                sql += " AND id='" + condition.getId() + "'";
            }
            if (condition.getName() != null && !condition.getName().isEmpty()) {
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
        }
        sql += " ORDER BY id DESC LIMIT ?,?";

        System.out.println("DAO查询find(condition, start, num) : " + sql);

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, num);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setCreateTime(rs.getLong("createTime"));
                user.setLastAccessTime(rs.getLong("lastAccessTime"));
                user.setStatus(rs.getString("status"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询用户出错：" + sql + "," + e.getMessage());
        }

        return userList;
    }

    @Override
    public int insert(User user) {
        int rows = 0;
        String sql = "INSERT INTO user_table(name,password,createTime,status) VALUES(?,?,?,?) ";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setLong(3, new Date().getTime());
            pstmt.setString(4, user.getStatus().getName());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO添加用户出错：" + sql + "," + e.getMessage());
        }

        return rows;
    }

    @Override
    public int update(User user) {
        int rows = 0;
        String sql = "UPDATE user_table SET name=? WHERE id=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getId());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO修改用户出错：" + sql + "," + e.getMessage());
        }

        return rows;
    }

    @Override
    public int updateAccessTime(User user) {
        int rows = 0;
        String sql = "UPDATE user_table SET lastAccessTime=? WHERE id=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, user.getLastAccessTime());
            pstmt.setInt(2, user.getId());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO修改用户出错：" + sql + "," + e.getMessage());
        }

        return rows;
    }

    @Override
    public int updatePwd(int id, String newPwd) {
        int rows = 0;
        String sql = "UPDATE user_table SET password=? WHERE id=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPwd);
            pstmt.setInt(2, id);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO重置用户密码出错：" + sql + "," + e.getMessage());
        }

        return rows;
    }

    @Override
    public int updateStatus(int id, String status) {
        int rows = 0;
        String sql = "UPDATE user_table SET status=? WHERE id=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO修改用户状态出错：" + sql + "," + e.getMessage());
        }

        return rows;
    }

    @Override
    public int delete(int id) {
        int rows = 0;
        String sql = "DELETE FROM user_table WHERE id=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO删除用户出错：" + sql + "," + e.getMessage());
        }

        return rows;
    }

    @Override
    public int count() {
        return count(null);
    }

    @Override
    public int count(User condition) {
        int num = 0;
        String sql = "SELECT count(*) FROM user_table WHERE 1=1";

        if (condition != null) {
            if (condition.getId() != 0) {
                sql += " AND id='" + condition.getId() + "'";
            }
            if (condition.getName() != null && !condition.getName().isEmpty()) {
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
        }
        System.out.println("DAO查询count(con): " + sql);

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询用户数量出错：" + sql + "," + e.getMessage());
        }

        return num;
    }
}

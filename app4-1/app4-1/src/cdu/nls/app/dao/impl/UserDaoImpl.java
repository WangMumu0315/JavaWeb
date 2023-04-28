package cdu.nls.app.dao.impl;

import cdu.nls.app.dao.UserDao;
import cdu.nls.app.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {


    @Override
    public User get(int id) {
        User user = null;
        String sql = "SELECT * FROM table_user WHERE id=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getInt("isAdmin"));
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取用户失败: id=" + id);
            e.printStackTrace();
        }

        return user;
    }


    @Override
    public User get(String name, String password) {
        User user = null;
        String sql = "SELECT * FROM table_user WHERE name=? AND password=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getInt("isAdmin"));
//                System.out.println(user.getIsAdmin());
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取登录用户失败：name=" + name + ", password=" + password);
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<User>();
        String sql = "SELECT * FROM table_user ORDER BY name";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getInt("isAdmin"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取用户列表失败");
            e.printStackTrace();
        }

        return userList;
    }


    @Override
    public List<User> findByPage(int start, int num) {
        List<User> userList = new ArrayList<User>();
        String sql = "SELECT * FROM table_user ORDER BY id LIMIT ?,?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, num);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getInt("isAdmin"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("DAO 分页获取用户列表失败: ");
            e.printStackTrace();
        }

        return userList;
    }


    @Override
    public int insert(User user) {
        int rows = 0;
        String sql = "INSERT INTO table_user(name,password) VALUES(?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            rows = pstmt.executeUpdate();
            System.out.println("DAO 添加用户: " + rows + ", " + user);
        } catch (SQLException e) {
            System.out.println("DAO 添加用户失败");
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int update(User user) {
        int rows = 0;
        String sql = "UPDATE table_user SET name=?,password=?,isAdmin=? WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getIsAdmin() ? 1 : 0);
            pstmt.setInt(4, user.getId());
            rows = pstmt.executeUpdate();
            System.out.println("DAO 修改用户: " + rows + ", " + user);
        } catch (SQLException e) {
            System.out.println("DAO 修改用户失败");
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int delete(int id) {
        int rows = 0;
        String sql = "DELETE FROM table_user WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rows = pstmt.executeUpdate();
            System.out.println("DAO 删除用户: " + rows + ", id=" + id);
        } catch (SQLException e) {
            System.out.println("DAO 删除用户失败");
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int count() {
        int count = 0;
        String sql = "SELECT count(*) FROM table_user";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取用户记录总数失败");
            e.printStackTrace();
        }
        return count;
    }
}

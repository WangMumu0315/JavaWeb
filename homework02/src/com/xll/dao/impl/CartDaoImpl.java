package com.xll.dao.impl;

import com.xll.dao.BaseDao;
import com.xll.dao.CartDao;
import com.xll.model.CartItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public class CartDaoImpl extends BaseDao implements CartDao {


    @Override
    public List<CartItem> findByCustomer(int customerId) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM cart_table WHERE customerId=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setCustomerId(rs.getInt("customerId"));
                cartItem.setBookId(rs.getInt("bookId"));
                cartItem.setNum(rs.getInt("num"));
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询购物车出错：customerId=" + customerId + "," + e.getMessage());
        }
        return cartItems;
    }


    @Override
    public int getNum(int customerId, int bookId) {
        int num = 0;
        String sql = "SELECT num FROM cart_table WHERE customerId=? and bookId=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, bookId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                num = rs.getInt("num");
            }
        } catch (SQLException e) {
            System.out.println("DAO查询购物车中图书：customerId=" + customerId + ", bookId=" + bookId + "," + e.getMessage());
        }

        return num;
    }

    @Override
    public int insert(CartItem cartItem) {
        int rows = 0;
        String sql = "INSERT INTO cart_table(customerId,bookId,num) VALUES (?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cartItem.getCustomerId());
            pstmt.setInt(2, cartItem.getBookId());
            pstmt.setInt(3, cartItem.getNum());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO向购物车中添加图书：" + cartItem + "," + e.getMessage());
        }

        return rows;
    }

    @Override
    public int update(CartItem cartItem) {
        int rows = 0;
        String sql = "UPDATE cart_table SET num=? WHERE customerId=? AND bookId=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cartItem.getNum());
            pstmt.setInt(2, cartItem.getCustomerId());
            pstmt.setInt(3, cartItem.getBookId());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO修改购物车中图书数量：" + cartItem + "," + e.getMessage());
        }

        return rows;
    }

    @Override
    public int delete(int customerId, int bookId) {
        int rows = 0;
        String sql = "DELETE FROM cart_table WHERE customerId=? AND bookId=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, bookId);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO删除购物车中图书：customerId=" + customerId + ", bookId=" + bookId + ", " + e.getMessage());
        }

        return rows;
    }

    @Override
    public int delete(int customerId) {
        int rows = 0;
        String sql = "DELETE FROM cart_table WHERE customerId=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerId);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO清空购物车：customerId=" + customerId + "," + e.getMessage());
        }

        return rows;
    }


}


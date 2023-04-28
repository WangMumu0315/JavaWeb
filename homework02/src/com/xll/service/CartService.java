package com.xll.service;

import com.xll.model.BookItem;
import com.xll.model.Cart;
import com.xll.model.User;

import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public interface CartService {


    //获取顾客数据库中已有的购物车信息
    Cart get(User customer);

    int find(List<BookItem> bookItemList, int bookId);

    //向购物车中添加图书
    Cart putIn(Cart cart, int bookId);

    //从购物车中删除图书
    Cart putOut(Cart cart, int bookId);

    //将数据库中的信息合并到购物车
    Cart merge(Cart cart, User customer);

    //在数据库中保存已登录用户的购物车信息
    void save(User customer, BookItem bookItem);

    //在数据库中删除已登录用户的购物车信息
    void del(User customer, int bookId);

    //清空数据库中的购物车
    void clear(User customer);


}

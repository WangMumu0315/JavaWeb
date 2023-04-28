package com.xll.service.impl;

import com.xll.dao.CartDao;
import com.xll.dao.impl.CartDaoImpl;
import com.xll.model.BookItem;
import com.xll.model.Cart;
import com.xll.model.CartItem;
import com.xll.model.User;
import com.xll.service.BookService;
import com.xll.service.CartService;
import com.xll.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public class CartServiceImpl implements CartService {

    CartDao cartDao = new CartDaoImpl();
    BookService bookService = new BookServiceImpl();
    CustomerService customerService = new CustomerServiceImpl();

    @Override
    public Cart get(User customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);

        List<CartItem> cartItems = cartDao.findByCustomer(customer.getId());
        List<BookItem> bookItemList = new ArrayList<>();
        if (cartItems != null) {
            for (CartItem cartItem : cartItems) {
                BookItem bookItem = new BookItem();
                bookItem.setBook(bookService.get(cartItem.getBookId()));
                bookItem.setNum(cartItem.getNum());
                bookItemList.add(bookItem);
            }
        }
        cart.setBookItemList(bookItemList);

        return cart;
    }

    @Override
    public int find(List<BookItem> bookItemList, int bookId) {
        int index = -1;
        if (bookItemList != null) {
            for (int i = 0; i < bookItemList.size(); i++) {
                if (bookItemList.get(i).getBook().getId() == bookId) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    @Override
    public Cart putIn(Cart cart, int bookId) {
        if (cart == null) {
            cart = new Cart();
        }

        //检查购物车中是否存在要购买的图书项
        List<BookItem> bookItemList = cart.getBookItemList();
        int pos = find(bookItemList, bookId);
        BookItem bookItem = null;
        if (pos == -1) {
            //购物车中没有要购买的图书项，则构造并添加新图书项
            bookItem = new BookItem();
            bookItem.setBook(bookService.get(bookId));
            bookItem.setNum(1);
//            System.out.println("bookItem ++++++++" + bookItem);
            bookItemList.add(bookItem);
        } else {
            //购物车中已有要购买的图书项，更新图书购买数量
            bookItem = bookItemList.get(pos);
            bookItem.setNum(bookItem.getNum() + 1);
            bookItemList.set(pos, bookItem);
        }

        //如果购物车属于已登录用户，则在数据库中保存购物项
        if (cart.getCustomer() != null) {
            save(cart.getCustomer(), bookItem);
        }

        //更新购物车
        cart.setBookItemList(bookItemList);

        return cart;
    }

    @Override
    public Cart putOut(Cart cart, int bookId) {
        if (cart == null) {
            return new Cart();
        }

        //检查购物车中是否存在要购买的图书项
        List<BookItem> bookItemList = cart.getBookItemList();
        int pos = find(bookItemList, bookId);

        if (pos != -1) {
            //从图书项列表中删除图书
            bookItemList.remove(pos);
            //更新购物车
            cart.setBookItemList(bookItemList);

            //如果购物车属于已登录用户，则在数据库删除图书项
            if (cart.getCustomer() != null) {
                del(cart.getCustomer(), bookId);
            }
        }

        return cart;
    }

    @Override
    public Cart merge(Cart cart, User customer) {
        if (cart == null) {
            return new Cart();
        }
        cart.setCustomer(customer);

        List<BookItem> bookItemList = cart.getBookItemList();
        if (bookItemList != null) {
            for (BookItem bookItem : bookItemList) {
                save(customer, bookItem);
            }
        }
        return get(cart.getCustomer());
    }

    @Override
    public void save(User customer, BookItem bookItem) {
        int oldNum = cartDao.getNum(customer.getId(), bookItem.getBook().getId());

        CartItem cartItem = new CartItem();
        cartItem.setCustomerId(customer.getId());
        cartItem.setBookId(bookItem.getBook().getId());

        if (oldNum == 0) {
            //购物车数据库中没有要购买的图书，向数据库中添加记录
            cartItem.setNum(bookItem.getNum());
            cartDao.insert(cartItem);
        } else {
            //购物车数据库中已有要购买的图书，修改数据库中记录
            cartItem.setNum(oldNum + bookItem.getNum());
            cartDao.update(cartItem);
        }
    }

    @Override
    public void del(User customer, int bookId) {
        cartDao.delete(customer.getId(), bookId);
    }

    @Override
    public void clear(User customer) {
        cartDao.delete(customer.getId());
    }

}

package com.xll.model;

import com.xll.util.FormatUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public class Cart {

    private User customer;
    private List<BookItem> bookItemList;
    // private Map<Integer, Book> bookMap;
    // private Map<Integer, Integer> bookNumMap;

    public Cart() {
        bookItemList = new ArrayList<>();
        // bookMap = new HashMap<>();
        // bookNumMap = new HashMap<>();
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<BookItem> getBookItemList() {
        return bookItemList;
    }

    public void setBookItemList(List<BookItem> bookItemList) {
        this.bookItemList = bookItemList;
    }

    // public Map<Integer, Book> getBookMap() {
    // return bookMap;
    // }
    //
    // public void setBookMap(Map<Integer, Book> bookMap) {
    // this.bookMap = bookMap;
    // }
    //
    // public Map<Integer, Integer> getBookNumMap() {
    // return bookNumMap;
    // }
    //
    // public void setBookNumMap(Map<Integer, Integer> bookNumMap) {
    // this.bookNumMap = bookNumMap;
    // }

    // public BigDecimal getTotalPrice() {
    // BigDecimal totalPrice = new BigDecimal(0);
    // for (int i = 0; i < bookMap.size(); i++) {
    // Book book = bookMap.get(i);
    // int num = bookNumMap.get(i);
    // BigDecimal price = book.getSalePrice().multiply(BigDecimal.valueOf(num));
    // totalPrice = totalPrice.add(price);
    // }
    // return totalPrice;
    // }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (BookItem bookItem : getBookItemList()) {
            Book book = bookItem.getBook();
            int num = bookItem.getNum();
            BigDecimal price = book.getSalePrice().multiply(BigDecimal.valueOf(num));
            totalPrice = totalPrice.add(price);
        }
        return totalPrice;
    }

    public String getTotalPriceString() {
        return FormatUtil.formatMoney(getTotalPrice());
    }

    public int size() {
        return getBookItemList().size();
    }

}


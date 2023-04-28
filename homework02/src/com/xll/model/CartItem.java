package com.xll.model;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public class CartItem {

    private int customerId;
    private int bookId;
    private int num; // 购买数量

    public CartItem() {
    }

    public CartItem(int customerId, int bookId, int num) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.num = num;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "customerId=" + customerId +
                ", bookId=" + bookId +
                ", num=" + num +
                '}';
    }
}

package com.xll.model;

import java.math.BigDecimal;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public class BookItem {

    private Book book;
    private int num;

    public BookItem(Book book, int num) {
        this.book = book;
        this.num = num;
    }

    public BookItem() {
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getNum() {
        if (num > getBook().getStock()) {
            return getBook().getStock();
        } else {
            return num;
        }
    }

    public void setNum(int num) {
        this.num = num;
    }

    public BigDecimal getBookPrice() {
        return book.getSalePrice().multiply(BigDecimal.valueOf(num));
    }

    @Override
    public String toString() {
        return book.getId() + ":" + num;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof BookItem) {
            BookItem item = (BookItem) obj;
            if (item.getBook().getId() == this.getBook().getId()) {
                return true;
            }
        }

        return false;
    }

}


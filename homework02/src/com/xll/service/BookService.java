package com.xll.service;

import com.xll.model.Book;

import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public interface BookService {

    Book get(int id);

    List<Book> get(Book condition, int page, int pageSize);

    int count(Book condition);

    boolean add(Book book);

    boolean mod(Book book);

    boolean del(int id);


}


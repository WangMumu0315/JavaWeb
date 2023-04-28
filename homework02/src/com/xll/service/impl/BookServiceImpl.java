package com.xll.service.impl;

import com.xll.dao.BookDao;
import com.xll.dao.impl.BookDaoImpl;
import com.xll.model.Book;
import com.xll.service.BookService;

import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public class BookServiceImpl implements BookService {

    BookDao bookDao = new BookDaoImpl();


    @Override
    public Book get(int id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> get(Book condition, int page, int pageSize) {
        return bookDao.query(condition, (page - 1) * pageSize, pageSize);
    }

    @Override
    public int count(Book condition) {
        return bookDao.count(condition);
    }

    @Override
    public boolean add(Book book) {
        return bookDao.insert(book) == 1;
    }

    @Override
    public boolean mod(Book book) {
        return bookDao.update(book) == 1;
    }

    @Override
    public boolean del(int id) {
        return bookDao.delete(id) == 1;
    }
}


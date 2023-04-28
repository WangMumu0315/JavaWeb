package com.xll.service.impl;

import com.xll.dao.UserDao;
import com.xll.dao.impl.CustomerDaoImpl;
import com.xll.model.User;
import com.xll.model.UserStatus;
import com.xll.service.CustomerService;
import com.xll.util.Encrypt;

import java.util.Date;
import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public class CustomerServiceImpl implements CustomerService {

    UserDao userDao = new CustomerDaoImpl();

    @Override
    public User get(int id) {
        return userDao.findById(id);
    }

    @Override
    public User get(User user) {
        return userDao.find(user.getName(), user.getPassword());
    }

    @Override
    public List<User> get(User condition, int page, int pageSize) {
        return userDao.query(condition, (page - 1) * pageSize, pageSize);
    }

    @Override
    public int count(User condition) {
        return userDao.count(condition);
    }

    @Override
    public boolean add(User user) {
        user.setPassword(Encrypt.toMd5(user.getPassword()));
        user.setStatus(UserStatus.NORMAL);
        return userDao.insert(user) == 1;
    }


    @Override
    public boolean mod(User user) {
        return userDao.update(user) == 1;
    }

    @Override
    public boolean modAccessTime(User customer) {
        customer.setLastAccessTime(new Date().getTime());
        return userDao.updateAccessTime(customer) == 1;
    }


    @Override
    public boolean resetPwd(int id) {
        String newPwd = Encrypt.toMd5("123");
        return userDao.updatePwd(id, newPwd) == 1;
    }

    @Override
    public boolean modStatus(int id, UserStatus status) {
        return userDao.updateStatus(id, status.getName()) == 1;
    }

    @Override
    public boolean checkStatus(User customer) {
        if (customer.getStatus() != null && customer.getStatus() == UserStatus.NORMAL) {
            return true;
        }
        return false;
    }


    @Override
    public boolean del(int id) {
        return userDao.delete(id) == 1;
    }
}

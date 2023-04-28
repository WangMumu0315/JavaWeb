package com.xll.service.impl;

import com.xll.dao.UserDao;
import com.xll.dao.impl.AdminUserDaoImpl;
import com.xll.model.User;
import com.xll.model.UserStatus;
import com.xll.service.AdminUserService;
import com.xll.util.Encrypt;

import java.util.Date;
import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public class AdminUserServiceImpl implements AdminUserService {

    UserDao adminUserDao = new AdminUserDaoImpl();

    @Override
    public User get(int id) {
        return adminUserDao.findById(id);
    }

    @Override
    public User get(User loginUser) {
        if (loginUser == null) {
            return null;
        }
        //密码加密
        loginUser.setPassword(Encrypt.toMd5(loginUser.getPassword()));
        User admin = adminUserDao.find(loginUser.getName(), loginUser.getPassword());
        if (admin != null) {
            //更新用户最近一次访问时间
            admin.setLastAccessTime(new Date());
            adminUserDao.updateAccessTime(admin);
        }
        return admin;
    }

    @Override
    public List<User> get(User condition, int page, int pageSize) {
        return adminUserDao.query(condition, (page - 1) * pageSize, pageSize);
    }

    @Override
    public int count(User condition) {
        return adminUserDao.count(condition);
    }

    @Override
    public boolean add(User adminUser) {
        adminUser.setPassword(Encrypt.toMd5(adminUser.getPassword()));
        adminUser.setStatus(UserStatus.NORMAL);
        return adminUserDao.insert(adminUser) == 1;
    }


    @Override
    public boolean mod(User adminUser) {
        return adminUserDao.update(adminUser) == 1;
    }

    @Override
    public boolean modAccessTime(User adminUser) {
        adminUser.setLastAccessTime(new Date().getTime());
        return adminUserDao.updateAccessTime(adminUser) == 1;
    }


    @Override
    public boolean resetPwd(int id) {
        String newPwd = Encrypt.toMd5("123");
        return adminUserDao.updatePwd(id, newPwd) == 1;
    }

    @Override
    public boolean modStatus(int id, UserStatus status) {
        return adminUserDao.updateStatus(id, status.getName()) == 1;
    }

    @Override
    public boolean checkStatus(User adminUser) {
        return adminUser.getStatus() != null && adminUser.getStatus() == UserStatus.NORMAL;
    }


    @Override
    public boolean del(int id) {
        return adminUserDao.delete(id) == 1;
    }
}


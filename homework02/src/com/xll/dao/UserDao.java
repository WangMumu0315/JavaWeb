package com.xll.dao;

import com.xll.model.User;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public interface UserDao extends SimpleDao<User> {

    //用于登录验证
    User find(String name, String password);

    //更新用户最近一次登录时间
    int updateAccessTime(User user);

    //密码重置
    int updatePwd(int id, String newPwd);

    //更新帐号状态
    int updateStatus(int id, String status);


}
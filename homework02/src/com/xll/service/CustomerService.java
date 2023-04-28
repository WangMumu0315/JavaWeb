package com.xll.service;

import com.xll.model.User;
import com.xll.model.UserStatus;

import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public interface CustomerService {

    User get(int id);

    User get(User user);

    List<User> get(User condition, int page, int pageSize);

    int count(User condition);

    boolean add(User user);

    boolean mod(User user);

    boolean modAccessTime(User customer);

    boolean resetPwd(int id);

    boolean modStatus(int id, UserStatus status);

    boolean checkStatus(User customer);

    boolean del(int id);


}

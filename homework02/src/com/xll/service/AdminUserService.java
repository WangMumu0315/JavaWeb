package com.xll.service;

import com.xll.model.User;
import com.xll.model.UserStatus;

import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public interface AdminUserService {

    User get(int id);

    User get(User adminUser);

    List<User> get(User condition, int page, int pageSize);

    int count(User condition);

    boolean add(User adminUser);

    boolean mod(User adminUser);

    boolean modAccessTime(User adminUser);

    boolean resetPwd(int id);

    boolean modStatus(int id, UserStatus status);

    boolean checkStatus(User adminUser);

    boolean del(int id);


}

package cn.edu.nuaa.burning.service;

import cn.edu.nuaa.burning.entity.User;

/**
 * Created by yuchuan.
 */
public interface UserService {

    boolean checkUserExist(String username);

    User login(String username, String password);
}

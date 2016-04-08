package cn.edu.nuaa.burning.service;

import cn.edu.nuaa.burning.entity.User;

/**
 * Created by yuchuan.
 */
public interface UserService {

    User addUser(String username, String password, String email);

    User findUser(String username, String password);
}

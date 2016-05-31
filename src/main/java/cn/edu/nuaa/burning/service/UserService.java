package cn.edu.nuaa.burning.service;

import cn.edu.nuaa.burning.domain.request.UserReq;
import cn.edu.nuaa.burning.entity.User;

/**
 * Created by yuchuan.
 */
public interface UserService {

    User addUser(UserReq userReq);

    User findUser(String username, String password);

    User updateUser(String id, UserReq userReq);

    User findUserById(String id);

    Boolean changePassword(String id, String oldPW, String newPW);
}

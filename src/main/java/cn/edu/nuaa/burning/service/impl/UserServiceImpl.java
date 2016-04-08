package cn.edu.nuaa.burning.service.impl;

import cn.edu.nuaa.burning.entity.User;
import cn.edu.nuaa.burning.exception.InvalidInputException;
import cn.edu.nuaa.burning.exception.ResourceAlreadyExistsException;
import cn.edu.nuaa.burning.exception.ResourceNotFoundException;
import cn.edu.nuaa.burning.repository.UserRepository;
import cn.edu.nuaa.burning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * qiyuey on 2016/4/8.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(String username, String password, String email) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)) {
            throw new InvalidInputException();
        }
        User user = userRepository.findByUsername(username);
        if (user != null) {
            throw new ResourceAlreadyExistsException();
        }
        user = new User();
        user.setUsername(username);
        user.setPassword(password); // FIXME 加密
        user.setEmail(email);
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User findUser(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new InvalidInputException();
        }
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new ResourceNotFoundException();
        }
        return user;
    }
}

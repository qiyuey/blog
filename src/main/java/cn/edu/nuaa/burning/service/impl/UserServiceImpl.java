package cn.edu.nuaa.burning.service.impl;

import cn.edu.nuaa.burning.domain.request.UserReq;
import cn.edu.nuaa.burning.entity.User;
import cn.edu.nuaa.burning.exception.InvalidInputException;
import cn.edu.nuaa.burning.exception.NotLoginException;
import cn.edu.nuaa.burning.exception.ResourceAlreadyExistsException;
import cn.edu.nuaa.burning.exception.ResourceNotFoundException;
import cn.edu.nuaa.burning.repository.UserRepository;
import cn.edu.nuaa.burning.service.UserService;
import cn.edu.nuaa.burning.util.EmptyUtils;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * qiyuey on 2016/4/8.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(UserReq userReq) {
        if (StringUtils.isEmpty(userReq.getPassword()) ||
                StringUtils.isEmpty(userReq.getNickname()) ||
                StringUtils.isEmpty(userReq.getEmail())
                ) {
            throw new InvalidInputException();
        }
        User user = userRepository.findByEmail(userReq.getEmail());
        if (user != null) {
            throw new ResourceAlreadyExistsException();
        }
        user = new User();
        user.setPassword(userReq.getPassword()); // FIXME 加密
        user.setEmail(userReq.getEmail());
        user.setNickname(userReq.getEmail());
        user.setAge(EmptyUtils.convert(userReq.getAge()));
        user.setPhoto(EmptyUtils.convert(userReq.getPhoto()));
        user.setSignature(EmptyUtils.convert(userReq.getPhoto()));
        user.setCategories(Sets.newHashSet());
        user.setCollections(Sets.newHashSet());
        return userRepository.save(user);
    }

    @Override
    public User findUser(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new InvalidInputException();
        }
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new ResourceNotFoundException();
        }
        return user;
    }

    @Override
    public User updateUser(String id, UserReq userReq) {
        User user = checkExists(id);
        if (userReq.getAge() != null) {
            user.setAge(userReq.getAge());
        }
        if (userReq.getNickname() != null) {
            user.setNickname(userReq.getNickname());
        }
        if (userReq.getPhoto() != null) {
            user.setPhoto(userReq.getPhoto());
        }
        if (userReq.getSignature() != null) {
            user.setSignature(userReq.getSignature());
        }
        return userRepository.save(user);
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public Boolean changePassword(String id, String oldPW, String newPW) {
        User user = checkExists(id);
        if (user.getPassword().equals(oldPW)) {
            user.setPassword(newPW);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    private User checkExists(String id) {
        if (EmptyUtils.check(id)) {
            throw new InvalidInputException();
        }
        User user = userRepository.findById(id);
        if (user == null) {
            throw new NotLoginException();
        }
        return user;
    }
}

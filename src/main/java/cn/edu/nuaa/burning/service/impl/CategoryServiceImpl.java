package cn.edu.nuaa.burning.service.impl;

import cn.edu.nuaa.burning.entity.Category;
import cn.edu.nuaa.burning.entity.User;
import cn.edu.nuaa.burning.exception.InvalidInputException;
import cn.edu.nuaa.burning.exception.ResourceAlreadyExistsException;
import cn.edu.nuaa.burning.exception.ResourceNotFoundException;
import cn.edu.nuaa.burning.repository.CategoryRepository;
import cn.edu.nuaa.burning.repository.UserRepository;
import cn.edu.nuaa.burning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Category服务层接口
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    @Autowired
    public CategoryServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(String value, String userId) {
        if (StringUtils.isEmpty(value)) {
            throw new InvalidInputException();
        }
        User user = checkExists(userId);
        Category category = categoryRepository.findByValue(value);
        if (category == null) {
            category = new Category();
            category.setValue(value);
            categoryRepository.save(category);
        }
        if (user.getCategories().add(category.getId())) {
            return category;
        } else {
            throw new ResourceAlreadyExistsException();
        }
    }

    @Override
    public List<Category> findAllCategory(String userId) {
        User user = checkExists(userId);
        HashSet<String> hashSet = user.getCategories();
        return hashSet
                .stream()
                .map(key -> categoryRepository.findOne(key))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(String id, String userId) {
        User user = checkExists(userId);
        if (user.getCategories().remove(id)) {
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * 判断用户是否存在
     * TODO 提取抽象
     *
     * @param id 用户id
     * @return 用户
     */
    private User checkExists(String id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new ResourceNotFoundException();
        }
        return user;
    }
}

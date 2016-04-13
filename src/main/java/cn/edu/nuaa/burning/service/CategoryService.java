package cn.edu.nuaa.burning.service;

import cn.edu.nuaa.burning.entity.Category;

import java.util.List;

/**
 * Category服务层
 */
public interface CategoryService {

    Category addCategory(String value, String userId);

    List<Category> findAllCategory(String userId);

    void deleteCategory(String id, String userId);
}

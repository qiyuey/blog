package cn.edu.nuaa.burning.service;

import cn.edu.nuaa.burning.entity.Category;

/**
 * Created by zxh on 2016/4/13.
 */
public interface CategoryService {

    public Category addCategory(String value);

    public Category findByCategoryByValue(String value);

    public Boolean deleteCategory(Category category);

}

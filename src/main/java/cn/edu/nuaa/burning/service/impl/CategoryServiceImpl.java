package cn.edu.nuaa.burning.service.impl;

import cn.edu.nuaa.burning.entity.Category;
import cn.edu.nuaa.burning.exception.InvalidInputException;
import cn.edu.nuaa.burning.exception.RepositoryException;
import cn.edu.nuaa.burning.exception.ResourceAlreadyExistsException;
import cn.edu.nuaa.burning.exception.ResourceNotFoundException;
import cn.edu.nuaa.burning.repository.CategoryRespository;
import cn.edu.nuaa.burning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by zxh on 2016/4/13.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRespository categoryRespository;

    @Override
    public Category addCategory(String value){
        if (StringUtils.isEmpty(value)) {
            throw new InvalidInputException();
        }
        Category category = categoryRespository.findByValue(value);
        if (category != null) {
            throw new ResourceAlreadyExistsException();
        }
        category = new Category();
        category.setValue(value);
        category = categoryRespository.save(category);
        return category;
    }

    @Override
    public Category findByCategoryByValue(String value){
        if (StringUtils.isEmpty(value)) {
            throw new InvalidInputException();
        }
        Category category = categoryRespository.findByValue(value);
        if (category == null) {
            throw new ResourceNotFoundException();
        }
        return category;
    }

    @Override
    public Boolean deleteCategory(Category category){
        try {
            categoryRespository.delete(category);
        }catch (Exception e){
            throw new RepositoryException();
        }
        return true;
    }

}

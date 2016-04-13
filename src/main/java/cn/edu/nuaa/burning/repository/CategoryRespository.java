package cn.edu.nuaa.burning.repository;

import cn.edu.nuaa.burning.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zxh on 2016/4/13.
 */
@Repository
public interface CategoryRespository extends MongoRepository<Category, String> {

    Category findByValue(String value);

}

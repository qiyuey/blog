package cn.edu.nuaa.burning.repository;

import cn.edu.nuaa.burning.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Category数据访问类
 */
@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    Category findByValue(String value);
}

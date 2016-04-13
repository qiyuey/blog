package cn.edu.nuaa.burning.repository;

import cn.edu.nuaa.burning.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuchuan.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findById(String id);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}

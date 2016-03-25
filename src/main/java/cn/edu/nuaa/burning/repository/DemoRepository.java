package cn.edu.nuaa.burning.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.edu.nuaa.burning.entity.Demo;

/**
 * Created by yuchuan.
 */
@Repository
public interface DemoRepository extends MongoRepository<Demo, String> {

    Demo findById(String id);

    Page<Demo> findAll(Pageable pageable);
}

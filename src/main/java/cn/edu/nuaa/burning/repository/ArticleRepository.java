package cn.edu.nuaa.burning.repository;

import cn.edu.nuaa.burning.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author qiyuey
 */
@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

    Slice<Article> findByUserId(String userId, Pageable pageable);

    Article findByIdAndUserId(String id, String userId);
}

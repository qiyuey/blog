package cn.edu.nuaa.burning.service;

import cn.edu.nuaa.burning.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

/**
 * @author qiyuey
 */
public interface ArticleService {

    Slice<Article> findArticleSlice(String userId, Pageable pageable);

    Article addArticle(Article article);

    Article updateArticle(Article article);

    Article findArticle(String id);

    void deleteArticle(String userId, String id);

    List<String> findLikeById(String id);

    void deleteLike(String userId, String id);
}

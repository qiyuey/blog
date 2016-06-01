package cn.edu.nuaa.burning.service;

import cn.edu.nuaa.burning.entity.Article;
import cn.edu.nuaa.burning.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

/**
 * @author qiyuey
 */
public interface ArticleService {

    Slice<Article> findAllArticleSlice(Pageable pageable);

    Slice<Article> findAllArticleSliceByCategory(String categoryId, Pageable pageable);

    Slice<Article> findArticleSlice(String userId, Pageable pageable);

    Slice<Article> findArticleSliceByCategory(String userId, String categoryId, Pageable pageable);

    Article addArticle(Article article);

    Article updateArticle(Article article);

    Article findArticle(String id);

    void deleteArticle(String userId, String id);

    List<String> findLikeById(String id);

    List<String> addLike(String userId, String id);

    void deleteLike(String userId, String id);

    List<Comment> findCommentByArticle(String id);

    Comment addComment(String id, Comment comment);
}

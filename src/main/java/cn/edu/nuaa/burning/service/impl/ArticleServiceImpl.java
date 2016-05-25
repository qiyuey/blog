package cn.edu.nuaa.burning.service.impl;

import cn.edu.nuaa.burning.entity.Article;
import cn.edu.nuaa.burning.entity.Comment;
import cn.edu.nuaa.burning.exception.InvalidInputException;
import cn.edu.nuaa.burning.exception.PermissionException;
import cn.edu.nuaa.burning.exception.ResourceNotFoundException;
import cn.edu.nuaa.burning.repository.ArticleRepository;
import cn.edu.nuaa.burning.repository.CategoryRepository;
import cn.edu.nuaa.burning.service.ArticleService;
import cn.edu.nuaa.burning.util.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author qiyuey
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Slice<Article> findAllArticleSlice(Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        return articleRepository.findAll(pageable);
    }

    @Override
    public Slice<Article> findArticleSlice(String userId, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        return articleRepository.findByUserId(userId, pageable);
    }

    @Override
    public Article addArticle(Article article) {
        article.setId(null);
        if (article.getTitle() == null || article.getContent() == null || article.getSummary() == null) {
            throw new InvalidInputException();
        }
        if (categoryRepository.findOne(article.getCategoryId()) == null) {
            throw new ResourceNotFoundException();
        }
        if (article.getTags() == null) {
            article.setTags(new ArrayList<>());
        }
        article.setCreateTime(new Date());
        article.setModifyTime(new Date());
        article.setComments(new ArrayList<>());
        article.setLikes(new ArrayList<>());
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Article article) {
        if (article.getTitle() == null || article.getContent() == null || article.getSummary() == null) {
            throw new InvalidInputException();
            //throws关键字通常被应用在声明方法时，用来指定可能抛出的异常
            /*程序在执行到throw语句时立即停止，它后面的语句都不执行。通过throw抛出异常后，如果想在上一级代码中来捕获并处理异常，
            则需要在抛出异常的方法中使用throws关键字在方法声明中指明要跑出的异常；如果要捕捉throw抛出的异常，则必须使用try—catch语句
             */
        }
        Article entity = articleRepository.findByIdAndUserId(article.getId(), article.getUserId());
        if (entity == null) {
            throw new ResourceNotFoundException();
        }
        if (!Objects.equals(entity.getCategoryId(), article.getCategoryId())) {
            if (categoryRepository.findOne(article.getCategoryId()) == null) {
                throw new ResourceNotFoundException();
            }
            entity.setCategoryId(article.getCategoryId());
        }
        entity.setTags(article.getTags());
        entity.setContent(article.getContent());
        entity.setSummary(article.getSummary());
        entity.setTitle(article.getTitle());
        entity.setModifyTime(new Date());
        return articleRepository.save(entity);
    }

    @Override
    public Article findArticle(String id) {
        if (EmptyUtils.check(id)) {
            throw new InvalidInputException();
        }
        Article article = articleRepository.findOne(id);
        if (article == null) {
            throw new ResourceNotFoundException();
        }
        return article;
    }

    @Override
    public void deleteArticle(String userId, String id) {
        Article article = checkExists(id);
        if (!Objects.equals(id, article.getUserId())) {
            throw new PermissionException();
        }
        articleRepository.delete(id);
    }

    @Override
    public List<String> findLikeById(String id) {
        Article article = checkExists(id);
        return article.getLikes();
    }

    @Override
    public List<String> addLike(String userId, String id) {
        Article article = checkExists(id);
        article.getLikes().add(userId);
        articleRepository.save(article);
        return article.getLikes();
    }

    // 点赞

    @Override
    public void deleteLike(String userId, String id) {
        Article article = checkExists(id);
        int index = article.getLikes().lastIndexOf(userId);
        if (index >= 0) {
            article.getLikes().remove(userId);
            articleRepository.save(article);
        }
    }

    @Override
    public List<Comment> findCommentByArticle(String id) {
        Article article = checkExists(id);
        return article.getComments();
    }

    @Override
    public Comment addComment(String id, Comment comment) {
        Article article = checkExists(id);
        comment.setCreateTime(new Date());
        article.getComments().add(comment);
        return null;
    }

    private Article checkExists(String id) {
        if (EmptyUtils.check(id)) {
            throw new InvalidInputException();
        }
        Article article = articleRepository.findOne(id);
        if (article == null) {
            throw new ResourceNotFoundException();
        }
        return article;
    }
}

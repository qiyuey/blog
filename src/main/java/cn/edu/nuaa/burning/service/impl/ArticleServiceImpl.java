package cn.edu.nuaa.burning.service.impl;

import cn.edu.nuaa.burning.entity.Article;
import cn.edu.nuaa.burning.exception.InvalidInputException;
import cn.edu.nuaa.burning.exception.ResourceNotFoundException;
import cn.edu.nuaa.burning.repository.ArticleRepository;
import cn.edu.nuaa.burning.repository.CategoryRepository;
import cn.edu.nuaa.burning.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
}

package cn.edu.nuaa.burning.service.impl;

import cn.edu.nuaa.burning.entity.Article;
import cn.edu.nuaa.burning.repository.ArticleRepository;
import cn.edu.nuaa.burning.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

/**
 * @author qiyuey
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Slice<Article> findArticleSlice(String userId, Pageable pageable) {
        if (pageable == null) {
            pageable = new PageRequest(0, 10);
        }
        return articleRepository.findByUserId(userId, pageable);
    }
}

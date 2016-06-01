package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.domain.request.UserReq;
import cn.edu.nuaa.burning.entity.Article;
import cn.edu.nuaa.burning.entity.Category;
import cn.edu.nuaa.burning.entity.Comment;
import cn.edu.nuaa.burning.repository.CategoryRepository;
import cn.edu.nuaa.burning.service.ArticleService;
import cn.edu.nuaa.burning.service.impl.ArticleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author qiyuey
 */
@Slf4j
@Component
@Path("gets")
public class GetResource {



    private final CategoryRepository categoryRepository;
    @Autowired
    private ArticleService articleService;

    @Autowired
    public GetResource(@Qualifier("categoryRepository") CategoryRepository categoryRepository) {
        Assert.notNull(categoryRepository, "spring error");
        this.categoryRepository = categoryRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Article get() {
//        Article article = new Article();
//        article.setCategoryId("574d862e529867da5e743f8e");
//        article.setContent("hahahha");
//        article.setTitle("hi,yccy");
//        article.setSummary("hh");
//        article.setUserId("574c3510e4b01011ebe3a902");
//        articleService.addArticle(article);
        Comment comment = new Comment();
        comment.setContent("aaaaaaaaaaaaaaaaa");
        comment.setFromUserId("574c3510e4b01011ebe3a902");
        comment.setToUserId("574c3510e4b01011ebe3a902");
        articleService.addComment("574e484faa6304b66bd6398a", comment);
 //       public Category get() {
//        Category category = new Category();
//        Category category1 = new Category();
//        Category category2 = new Category();
//        Category category3 = new Category();
//        Category category4 = new Category();
//        category.setValue("感悟");
//        category1.setValue("励志");
//        category2.setValue("生活");
//        category3.setValue("箴言");
//        category4.setValue("幽默");
//        categoryRepository.save(category);
//        categoryRepository.save(category1);
//        categoryRepository.save(category2);
//        categoryRepository.save(category3);
//        categoryRepository.save(category4);
        return articleService.findArticle("574e484faa6304b66bd6398a");
    }
}

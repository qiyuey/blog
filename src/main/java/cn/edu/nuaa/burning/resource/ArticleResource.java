package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.entity.Article;
import cn.edu.nuaa.burning.exception.InvalidInputException;
import cn.edu.nuaa.burning.service.ArticleService;
import cn.edu.nuaa.burning.util.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author qiyuey
 */
@Component
@Path("article")
public class ArticleResource {

    private final ArticleService articleService;

    @Autowired
    public ArticleResource(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Slice<Article> getAll(
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("10") @QueryParam("size") Integer size,
            @Context HttpServletRequest request) {
        String id = PermissionUtils.findId(request);
        return articleService.findArticleSlice(id, new PageRequest(page, size));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Article create(Article article, @Context HttpServletRequest request) {
        String id = PermissionUtils.findId(request);
        if (article == null) {
            throw new InvalidInputException();
        }
        article.setUserId(id);
        return articleService.addArticle(article);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Article update(Article article, @Context HttpServletRequest request) {
        String id = PermissionUtils.findId(request);
        if (article == null) {
            throw new InvalidInputException();
        }
        article.setUserId(id);
        return articleService.updateArticle(article);
    }
}

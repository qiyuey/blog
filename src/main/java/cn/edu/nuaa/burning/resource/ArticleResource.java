package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.domain.response.UserResp;
import cn.edu.nuaa.burning.entity.Article;
import cn.edu.nuaa.burning.entity.Comment;
import cn.edu.nuaa.burning.entity.User;
import cn.edu.nuaa.burning.exception.InvalidInputException;
import cn.edu.nuaa.burning.service.ArticleService;
import cn.edu.nuaa.burning.service.UserService;
import cn.edu.nuaa.burning.util.PermissionUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;

/**
 * @author qiyuey
 */
@Component
@Path("articles")
public class ArticleResource {

    private final ArticleService articleService;

    private final UserService userService;

    @Autowired
    public ArticleResource(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Slice<Article> getAllArticle(
            @QueryParam("categoryId") String categoryId,
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("10") @QueryParam("size") Integer size,
            @Context HttpServletRequest request) {
        if (categoryId == null || Objects.equals(categoryId, "")) {
            return articleService.findAllArticleSlice(new PageRequest(page, size));
        } else {
            return articleService.findAllArticleSliceByCategory(categoryId, new PageRequest(page, size));
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Slice<Article> getArticle(
            @QueryParam("categoryId") String categoryId,
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("10") @QueryParam("size") Integer size,
            @Context HttpServletRequest request) {
        String id = PermissionUtils.findId(request);
        if (categoryId == null || Objects.equals(categoryId, "")) {
            return articleService.findArticleSlice(id, new PageRequest(page, size));
        } else {
            return articleService.findArticleSliceByCategory(id, categoryId, new PageRequest(page, size));
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article get(@PathParam("id") String id) {
        return articleService.findArticle(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Article create(Article article, @Context HttpServletRequest request) {
        String id = PermissionUtils.findId(request);//执行权限判断
        if (article == null) {
            throw new InvalidInputException();
        }
        article.setUserId(id);//文章需要通过id记录创建者，文章发给服务器的JSON里的id，
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

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") String id, @Context HttpServletRequest request) {
        String userId = PermissionUtils.findId(request);
        articleService.deleteArticle(userId, id);
    }

    @POST
    @Path("{articleId}/likes")
    public void addLikes(@PathParam("articleId") String id, @Context HttpServletRequest request) {
        String userId = PermissionUtils.findId(request);
        articleService.addLike(userId, id);
    }

    @DELETE
    @Path("{articleId}/likes/")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteLikes(@PathParam("articleId") String id, @Context HttpServletRequest request) {
        String userId = PermissionUtils.findId(request);
        articleService.deleteLike(userId, id);
    }

    @GET
    @Path("{articleId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getComments(@PathParam("articleId") String id) {
        return articleService.findCommentByArticle(id);
    }

    @POST
    @Path("{articleId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment addComment(Comment comment, @PathParam("articleId") String id, @Context HttpServletRequest request) {
        String userId = PermissionUtils.findId(request);
        comment.setFromUserId(userId);
        return articleService.addComment(id, comment);
    }
}

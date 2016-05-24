package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.domain.response.UserResp;
import cn.edu.nuaa.burning.entity.Article;
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

/**
 * @author qiyuey
 */
@Component
@Path("article")
public class ArticleResource {

    private final ArticleService articleService;

    private final UserService userService;

    @Autowired
    public ArticleResource(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
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

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") String id, @Context HttpServletRequest request) {
        String userId = PermissionUtils.findId(request);
        articleService.deleteArticle(userId, id);
    }

    @GET
    @Path("{articleId}/likes/")
    public List<UserResp> getLikes(@PathParam("articleId") String id) {
        List<String> userIds = articleService.findLikeById(id);
        List<UserResp> users = Lists.newArrayList();
        UserResp userResp;
        User user;
        for (int i = 0; i < userIds.size(); i++) {
            user = userService.findUserById(id);
            userResp = new UserResp(user);
            users.add(userResp);
        }
        return users;
    }

    @DELETE
    @Path("{articleId}/likes/")
    public void deleteLikes(@PathParam("articleId") String id, @Context HttpServletRequest request) {
        String userId = PermissionUtils.findId(request);
        articleService.deleteLike(userId, id);
    }
}

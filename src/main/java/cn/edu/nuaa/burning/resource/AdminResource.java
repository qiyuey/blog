package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.entity.Article;
import cn.edu.nuaa.burning.entity.User;
import cn.edu.nuaa.burning.exception.PermissionException;
import cn.edu.nuaa.burning.exception.ResourceNotFoundException;
import cn.edu.nuaa.burning.repository.ArticleRepository;
import cn.edu.nuaa.burning.repository.UserRepository;
import cn.edu.nuaa.burning.util.PermissionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

/**
 * @author qiyuey
 */
@Slf4j
@Component
@Path("admin")
public class AdminResource {


    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    @Autowired
    public AdminResource(@Qualifier("articleRepository") ArticleRepository articleRepository, @Qualifier("userRepository") UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean login(
            @QueryParam("username") String username,
            @QueryParam("password") String password,
            @Context HttpServletRequest request
    ) {
        if (Objects.equals(username, "admin") && Objects.equals(password, "yccy")) {
            request.getSession().setAttribute("admin", true);
            return true;
        } else {
            return false;
        }
    }

    @DELETE
    @Path("article/{articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response article(@PathParam("articleId") String id, @Context HttpServletRequest request) {
        if (!PermissionUtils.checkAdmin(request)) {
            throw new PermissionException();
        }
        articleRepository.delete(id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response user(@PathParam("userId") String id, @Context HttpServletRequest request) {
        if (!PermissionUtils.checkAdmin(request)) {
            throw new PermissionException();
        }
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new ResourceNotFoundException();
        }
        user.setPassword("!@$&*((");
        userRepository.save(user);
        return Response.noContent().build();
    }

    @DELETE
    @Path("article/{articleId}/comment/{position}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response comment(
            @PathParam("articleId") String id,
            @PathParam("position") int pos,
            @Context HttpServletRequest request
    ) {
        if (!PermissionUtils.checkAdmin(request)) {
            throw new PermissionException();
        }
        Article article = articleRepository.findOne(id);
        if (article == null) {
            throw new ResourceNotFoundException();
        }
        article.getComments().remove(pos);
        articleRepository.save(article);
        return Response.noContent().build();
    }
}

package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.domain.response.UserResp;
import cn.edu.nuaa.burning.entity.User;
import cn.edu.nuaa.burning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * qiyuey on 2016/4/8.
 */
@Component
@Path("user")
public class UserResource {

    @Autowired
    private UserService userService;

    @GET
    @Produces("application/json")
    public Response login(
            @QueryParam("username") String username,
            @QueryParam("password") String password,
            @Context HttpServletRequest request) {
        User user = userService.findUser(username, password);
        UserResp userResp = new UserResp(user);
        request.getSession().setAttribute("id", user.getId());
        return Response.ok(userResp).build();
    }

    @POST
    @Produces("application/json")
    public Response register(
            @QueryParam("username") String username,
            @QueryParam("password") String password,
            @QueryParam("email") String email,
            @Context HttpServletRequest request) {
        User user = userService.addUser(username, password, email);
        UserResp userResp = new UserResp(user);
        request.getSession().setAttribute("id", user.getId());
        return Response.ok(userResp).build();
    }
}

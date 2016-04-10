package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.domain.request.RegisterReq;
import cn.edu.nuaa.burning.domain.response.UserResp;
import cn.edu.nuaa.burning.entity.User;
import cn.edu.nuaa.burning.exception.InvalidInputException;
import cn.edu.nuaa.burning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
    @Produces(MediaType.APPLICATION_JSON)
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(
            RegisterReq registerReq,
            @Context HttpServletRequest request) {
        if (registerReq == null) {
            throw new InvalidInputException();
        }
        User user = userService.addUser(
                registerReq.getUsername(),
                registerReq.getPassword(),
                registerReq.getEmail());
        UserResp userResp = new UserResp(user);
        request.getSession().setAttribute("id", user.getId());
        return Response.ok(userResp).build();
    }
}

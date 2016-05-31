package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.domain.request.UserReq;
import cn.edu.nuaa.burning.domain.response.UserResp;
import cn.edu.nuaa.burning.entity.User;
import cn.edu.nuaa.burning.exception.InvalidInputException;
import cn.edu.nuaa.burning.service.UserService;
import cn.edu.nuaa.burning.util.PermissionUtils;
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
@Path("users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
            @QueryParam("email") String email,
            @QueryParam("password") String password,
            @Context HttpServletRequest request
    ) {
        User user = userService.findUser(email, password);
        UserResp userResp = new UserResp(user);
        request.getSession().setAttribute("id", user.getId());
        return Response.ok(userResp).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(
            UserReq userReq,
            @Context HttpServletRequest request
    ) {
        if (userReq == null) {
            throw new InvalidInputException();
        }
        User user = userService.addUser(userReq);
        request.getSession().setAttribute("id", user.getId());
        return userRespBuild(user);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("id") String id,
            UserReq userReq,
            @Context HttpServletRequest request
    ) {
        PermissionUtils.check(request, id);
        return userRespBuild(userService.updateUser(id, userReq));
    }

    @POST
    @Path("{id}/password")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(
            @PathParam("id") String id,
            @FormParam("old") String oldPW,
            @FormParam("new") String newPW,
            @Context HttpServletRequest request
            ) {
        PermissionUtils.check(request, id);
        return Response.ok(userService.changePassword(id, oldPW, newPW)).build();
    }

    private Response userRespBuild(User user) {
        UserResp userResp = new UserResp(user);
        return Response.ok(userResp).build();
    }
}

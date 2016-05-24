package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.service.CategoryService;
import cn.edu.nuaa.burning.util.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Category访问类
 */
@Component
@Path("categories")
public class CategoryResource {

    private final CategoryService categoryService;

    @Autowired
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context HttpServletRequest request) {
        return Response
                .ok(categoryService.findAllCategory(PermissionUtils.findId(request)))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(
            @QueryParam("value") String value,
            @Context HttpServletRequest request
    ) {
        String id = PermissionUtils.findId(request);
        return Response.ok(categoryService.addCategory(value, id)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("id") String id,
            @Context HttpServletRequest request
    ) {
        categoryService.deleteCategory(id, PermissionUtils.findId(request));
        // TODO 文章移动到默认分类或者禁止删除有文章的分类
        return Response.noContent().build();
    }

}

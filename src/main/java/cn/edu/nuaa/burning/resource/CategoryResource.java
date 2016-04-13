package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.domain.response.CategoryResp;
import cn.edu.nuaa.burning.entity.Category;
import cn.edu.nuaa.burning.exception.InvalidInputException;
import cn.edu.nuaa.burning.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by zxh on 2016/4/13.
 */
@Component
@Path("category")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(
            @QueryParam("value") String value) {
        Category category = categoryService.addCategory(value);
        CategoryResp categoryResp = new CategoryResp(category);
        return Response.ok(categoryResp).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @QueryParam("value") String value) {
        Category category = categoryService.findByCategoryByValue(value);
        categoryService.deleteCategory(category);
        return Response.noContent().build();
    }

}

package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.entity.Demo;
import cn.edu.nuaa.burning.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by yuchuan.
 */
@Slf4j
@Component
@Path("demo")
public class DemoResource {

    @Autowired
    private DemoService demoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Demo add(Demo demo, @Context HttpServletRequest request) {
        if (demo == null) {
            demo = new Demo();
            demo.setName(new Date().toString());
        }
        request.getSession().setAttribute("test", "ok");
        return demoService.addDemo(demo);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Slice<Demo> getAll(
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("10") @QueryParam("size") Integer size,
            @Context HttpServletRequest request) {
        log.info((String) request.getSession().getAttribute("test"));
        return demoService.getDemoSlice(new PageRequest(page, size));
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Demo update(Demo demo, @PathParam("id") String id) {
        Demo demoUp = demoService.getDemo(id);
        if (!StringUtils.isEmpty(demo.getName())) {
            demoUp.setName(demo.getName());
        }
        return demoService.updateDemo(demoUp);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Demo get(@PathParam("id") String id) {
        return demoService.getDemo(id);
    }
}

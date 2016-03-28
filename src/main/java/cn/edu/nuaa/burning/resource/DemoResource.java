package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.entity.Demo;
import cn.edu.nuaa.burning.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by yuchuan.
 */
@Component
@Path("demo")
public class DemoResource {

    @Autowired
    private DemoService demoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Demo add(Demo demo) {
        if (demo == null) {
            demo = new Demo();
            demo.setName(new Date().toString());
        }
        return demoService.addDemo(demo);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Slice<Demo> getAll(@DefaultValue("0") @QueryParam("page") Integer page, @DefaultValue("10") @QueryParam("size") Integer size) {
        return demoService.getDemoSlice(new PageRequest(page, size));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Demo get(@PathParam("id") String id) {
        return demoService.getDemo(id);
    }
}

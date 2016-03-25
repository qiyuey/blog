package cn.edu.nuaa.burning.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import cn.edu.nuaa.burning.entity.Demo;
import cn.edu.nuaa.burning.service.DemoService;

import java.util.Date;

/**
 * Created by yuchuan.
 */
@Component
@Path("/demo")
public class DemoResource {

    @Autowired
    private DemoService demoService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Demo add(Demo demo) {
        if (demo == null) {
            demo = new Demo();
            demo.setName(new Date().toString());
        }
        return demoService.addDemo(demo);
    }

    @GET
    @Produces("application/json")
    public Slice<Demo> getAll(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }
        Pageable pageable = new PageRequest(page, size);
        return demoService.getDemoSlice(pageable);
    }
}

package cn.edu.nuaa.burning.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import cn.edu.nuaa.burning.entity.Demo;
import cn.edu.nuaa.burning.service.DemoService;

/**
 * Created by yuchuan.
 */
@Component
@Path("/test")
public class DemoResource {

    @Autowired
    private DemoService demoService;

    @GET
    @Produces("application/json")
    public Demo getTest() {
        return demoService.get();
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public Slice<Demo> getAll() {
        return demoService.getAll();
    }
}

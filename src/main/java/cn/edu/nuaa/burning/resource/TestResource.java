package cn.edu.nuaa.burning.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.nuaa.burning.model.Test;
import cn.edu.nuaa.burning.service.TestService;
import org.springframework.stereotype.Component;

/**
 * Created by yuchuan.
 */
@Component
@Path("/test")
public class TestResource {

    @Autowired
    private TestService testService;

    @GET
    @Produces("application/json")
    public Test getTest() {
        return testService.get();
    }
}

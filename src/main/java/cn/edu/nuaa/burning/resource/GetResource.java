package cn.edu.nuaa.burning.resource;

import cn.edu.nuaa.burning.domain.request.UserReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author qiyuey
 */
@Slf4j
@Component
@Path("gets")
public class GetResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserReq get() {
        return new UserReq();
    }
}

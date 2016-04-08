package cn.edu.nuaa.burning.config;

import cn.edu.nuaa.burning.exception.BaseExceptionMapper;
import cn.edu.nuaa.burning.filter.PoweredByResponseFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yuchuan.
 */
@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("cn.edu.nuaa.burning.resource");
        register(BaseExceptionMapper.class);
        register(PoweredByResponseFilter.class);
    }
}
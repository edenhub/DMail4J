package pri.adam.dmail.proxy.applicationConf;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

/**
 * Created by adam on 2014/12/13.
 */
public class WebProxyApplicationConf extends ResourceConfig{

    public WebProxyApplicationConf(){
        packages(true,new String[]{"pri/adam/dmail/proxy/aaseServer","pri/adam/dmail/proxy/service/page"});
        register(LoggingFilter.class);
        register(FreemarkerMvcFeature.class);
    }
}

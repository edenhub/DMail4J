package pri.adam.dmail.proxy.service.page;

import org.glassfish.jersey.server.mvc.Viewable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by adam on 2014/12/14.
 */

@Path("page")
public class PageService {

    @Path("index")
    @GET
    @Produces("text/html")
    public Viewable toIndexPage(){

        return new Viewable("index.ftl");
    }
}

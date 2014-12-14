package pri.adam.dmail.proxy.aaseServer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by adam on 2014/12/13.
 */

@Path("/test")
public class MyResource {

    @GET
    @Produces("text/html")
    public String doTest(){
        return "HELLO DMAIL";
    }
}

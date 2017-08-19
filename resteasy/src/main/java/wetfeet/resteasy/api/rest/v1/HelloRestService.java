package wetfeet.resteasy.api.rest.v1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path ("/hello")
public class HelloRestService {

	@Path ("/test")
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getGreeting () {
		return Response.ok ("{ yo man }").build();
	}
	
}

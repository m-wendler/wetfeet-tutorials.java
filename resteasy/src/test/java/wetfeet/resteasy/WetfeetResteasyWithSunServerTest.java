package wetfeet.resteasy;

import java.net.InetSocketAddress;

import org.jboss.resteasy.plugins.server.sun.http.HttpContextBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import wetfeet.resteasy.api.rest.v1.HelloRestService;
import wetfeet.resteasy.api.rest.v1.Names;

import com.sun.net.httpserver.HttpServer;


/**
 * Tests the resources within an embedded sun web server.
 * 
 * @author Mike Wendler
 */
public class WetfeetResteasyWithSunServerTest 
	extends AbstractWetfeetResteasyTest
{

	/** the web server instance */
	protected static HttpServer server;

	/**
	 * Sets up the web server, adds the resources and starts it.
	 * 
	 * @throws Exception if anything fails
	 */
	@BeforeClass
	public static void setUpBeforeClass () 
		throws Exception 
	{
		server = HttpServer.create (new InetSocketAddress (PORT_NR), 10);
		HttpContextBuilder builder = new HttpContextBuilder ();
		
		builder.getDeployment().getActualResourceClasses().add (HelloRestService.class);
		builder.getDeployment().getActualResourceClasses().add (Names.class);

		builder.bind (server);
		
		server.start ();
	}
	
	/**
	 * Stops the web server.
	 * 
	 * @throws Exception if anything fails
	 */
	@AfterClass
	public static void tearDownAfterClass () 
		throws Exception 
	{
		server.stop (0);
	}

}
package wetfeet.resteasy;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;


/**
 * Tests the resources within an embedded jetty server.
 * Requires a war archive in the regular gradle place.
 * 
 * @author Mike Wendler
 */
public class WetfeetResteasyWithJettyTest 
	extends AbstractWetfeetResteasyTest
{
	
	/** the web server instance */
	private static Server server;	
	
	/**
	 * Sets up the web server, sets the war resource and starts it.
	 * 
	 * @throws Exception if anything fails
	 */
	@BeforeClass
	public static void setUpBeforeClass () 
		throws Exception 
	{
		server = new Server (PORT_NR);		
		
		WebAppContext context = new WebAppContext ();		
		context.setWar ("build/libs/wetfeet.resteasy.war");
		
		server.setHandler (context);
		
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
		server.stop ();
	}

}

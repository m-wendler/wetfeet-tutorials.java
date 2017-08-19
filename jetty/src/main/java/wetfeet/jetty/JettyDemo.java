package wetfeet.jetty;

import java.util.Properties;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;


/**
 * Demonstrates the programmatic configuration and start of Jetty. 
 * 
 * @author Mike Wendler
 */
public class JettyDemo {

	/**
	 * Creates a Jetty server object, configures and starts it.
	 * 
	 * @param args command line arguments (no-op)
	 * @throws Exception if setting up and starting Jetty or the port number type conversion fails
	 */
	public static void main (final String[] args) 
		throws Exception 
	{
		System.out.println ("******************************* wetfeet.jetty STARTED ... *************************************\n");

		// obtain the port number
		Properties properties = new Properties ();
		properties.load (JettyDemo.class.getClassLoader ().getResourceAsStream ("jetty.properties") );

		String portNrStr = properties.getProperty ("portNr");
		int portNr       = Integer.parseInt (portNrStr);


		// create server object
		Server jetty = new Server (portNr);


		// configure server
		System.out.println ("configuring Jetty to serve a welcome file 'index.html' from the current working directory ...");
		ResourceHandler resourceHandler = new ResourceHandler ();
		resourceHandler.setWelcomeFiles (new String [] { "index.html" });
		resourceHandler.setResourceBase (".");

		HandlerList handlers = new HandlerList ();
		handlers.setHandlers (new Handler [] { resourceHandler, new DefaultHandler () });
		jetty.setHandler (handlers);


		System.out.println (String.format ("starting Jetty to listen on %s ... \n", portNrStr) );
		jetty.start ();

		System.out.println (String.format ("\npoint your browser to: 'http://localhost:%s/index.html' ... \n", portNrStr) );

		System.out.println ("\n******************************* wetfeet.jetty DONE ... *************************************");
	}

}

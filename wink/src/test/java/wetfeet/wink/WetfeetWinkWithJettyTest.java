package wetfeet.wink;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.wink.server.internal.servlet.RestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import wetfeet.rest.wink.api.rest.v1.Names;

/**
 * Tests the resources within an embedded jetty server.
 * 
 * @author Mike
 */
public class WetfeetWinkWithJettyTest {

	/** the port nr to use */
	protected final static int PORT_NR     = 8787;
		
	/** an apache http client to reuse */
	protected HttpClient httpClient        = new DefaultHttpClient ();

	/** the desired host name */
	protected final static String hostName = "localhost";

	/** the web server instance */
	private static Server server;	

	/** Configures and runs jetty.
	 * 
	 * @throws Exception if anything fails
	 */
	@BeforeClass
	public static void setUpBeforeClass () 
		throws Exception 
	{
		server = new Server (PORT_NR);
		ServletContextHandler context = new ServletContextHandler (ServletContextHandler.SESSIONS);
		context.setContextPath ("/");
		
		// create a holder for the regular wink servlet
		ServletHolder holder = new ServletHolder (new RestServlet () );
		holder.setInitParameter ("javax.ws.rs.Application", "wetfeet.rest.wink.api.rest.v1.WinkApplication");
		
		// pass everything to jetty and start it
		context.addServlet (holder, "/rest/*");
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

	/**
	 * Tests the {@link Names} resource.
	 * 
	 * @throws ClientProtocolException if setting up the client protocol fails
	 * @throws IOException if a client-server interaction or stream read fails
	 */
	@Test
	public void testNames () 
		throws ClientProtocolException, IOException 
	{
		String resourcePath   = "rest/v1/names";		
		String baseURI        = MessageFormat.format ("http://{0}:{1}/{2}", hostName, PORT_NR + "", resourcePath);
		
		// test adding a name
		HttpResponse 
		resp = addName (baseURI);		
		assertEquals (201, resp.getStatusLine ().getStatusCode () ); 
		assertEquals ("", getResponseContent (resp));
		
		
		// test changing this name to a new one
		resp = updateName (baseURI); 
		assertEquals (200, resp.getStatusLine ().getStatusCode () ); 
		assertEquals ("", getResponseContent (resp));

		
		// test the current names
		resp = getNames (baseURI);		
		assertEquals (200, resp.getStatusLine ().getStatusCode () ); 
		assertEquals ("[Jerry]", getResponseContent (resp));

		
		// test deleting the name again
		resp = deleteName  (baseURI);
		assertEquals (204, resp.getStatusLine ().getStatusCode () ); 
		assertEquals ("", getResponseContent (resp));
		
		
		// test the current names again
		resp = getNames (baseURI);		
		assertEquals (200, resp.getStatusLine ().getStatusCode () ); 
		assertEquals ("[]", getResponseContent (resp));		
	}
	
	
	/**
	 * Deletes a name.
	 * 
	 * @param baseURI the base URI to use for this REST call.
	 * @return the response object from this REST call.
	 * @throws ClientProtocolException if setting up the client protocol fails
	 * @throws IOException if a client-server interaction or stream read fails
	 */
	private HttpResponse deleteName (final String baseURI) 
		throws ClientProtocolException, IOException 
	{
		HttpDelete delete = new HttpDelete (baseURI + "/Jerry");
		return httpClient.execute (delete);		
	}

	/**
	 * Gets all names.
	 * 
	 * @param baseURI the base URI to use for this REST call.
	 * @return the response object from this REST call.
	 * @throws ClientProtocolException if setting up the client protocol fails
	 * @throws IOException if a client-server interaction or stream read fails
	 */
	 private HttpResponse getNames (String baseURI) 
		throws ClientProtocolException, IOException 
	{
		HttpGet get = new HttpGet (baseURI); 
		return httpClient.execute (get);	
	}

	/**
	 * Updates a name.
	 * 
	 * @param baseURI the base URI to use for this REST call.
	 * @return the response object from this REST call.
	 * @throws ClientProtocolException if setting up the client protocol fails
	 * @throws IOException if a client-server interaction or stream read fails
	 */
	private HttpResponse updateName (final String baseURI) 
		throws ClientProtocolException, IOException 
	{
		HttpPut put = new HttpPut (baseURI + "/Ben?newName=Jerry");
		return httpClient.execute (put);
	}

	/**
	 * Adds a name.
	 * 
	 * @param baseURI the base URI to use for this REST call.
	 * @return the response object from this REST call.
	 * @throws ClientProtocolException if setting up the client protocol fails
	 * @throws IOException if a client-server interaction or stream read fails
	 */
	private HttpResponse addName (final String baseURI) 
		throws ClientProtocolException, IOException 
	{
		HttpPost post = new HttpPost (baseURI + "?name=Ben");		
		post.setHeader ("Accept", "application/json");
		post.setHeader ("Content-Type", "application/x-www-form-urlencoded");

		return httpClient.execute (post);
	}

	/**
	 * @return the content string (if there is any) 
	 * @param response the http response object
	 * @throws IOException if an IO operation fails
	 */
	private static String getResponseContent (final HttpResponse response) 
		throws IOException 
	{
		String result = "";

		// we need to check that there actually IS content (e.g. in the case of delete there'll be none)
		if (   null != response.getEntity () 
			&& null != response.getEntity ().getContent () ) 
		{
			InputStream content   = response.getEntity ().getContent ();
			InputStreamReader isr = new InputStreamReader (content);
			BufferedReader br     = new BufferedReader (isr);

			String line;
			try {
				while ( (line = br.readLine () ) != null) {
					result = line;
				}
			} finally {
				br.close ();
				isr.close ();
			}
		}
		
		return result;
	}

}

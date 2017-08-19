package wetfeet.resteasy;

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
import org.junit.Test;

import wetfeet.resteasy.api.rest.v1.HelloRestService;
import wetfeet.resteasy.api.rest.v1.Names;


/**
 * Tests the resteasy demo resources within an embedded web server.
 * This is being setup in a concrete sub class to separate concerns.
 * 
 * @author Mike Wendler.
 */
public abstract class AbstractWetfeetResteasyTest {

	/** the port nr to use */
	protected final static int PORT_NR     = 8585;
		
	/** an apache http client to reuse */
	protected HttpClient httpClient        = new DefaultHttpClient ();

	/** the desired host name */
	protected final static String hostName = "localhost";
		
	/**
	 * Tests the {@link HelloRestService} resource.
	 * 
	 * @throws ClientProtocolException if setting up the client protocol fails
	 * @throws IOException if a client-server interaction or stream read fails
	 */
	@Test
	public void testHelloRestService () 
		throws ClientProtocolException, IOException 
	{
		String resourcePath   = "hello/test";
		String baseURI        = MessageFormat.format ("http://{0}:{1}/{2}", hostName, PORT_NR + "", resourcePath);

		HttpGet get           = new HttpGet (baseURI);
		HttpResponse resp     = httpClient.execute (get);		
		int responseCode      = resp.getStatusLine ().getStatusCode ();

		assertEquals (200, responseCode); 	
		assertEquals ("{ yo man }", getResponseContent (resp) );
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
		String resourcePath   = "v1/names";		
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


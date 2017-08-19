package wetfeet.resteasy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * Demonstrates accessing RESTful webservices.
 * Is actually clompletely independent from resteasy. In fact this is the 
 * same code that I used for accessing wink services.
 *  
 * @author Mike Wendler
 */
public class ResteasyDemo {

	/**
	 * Accesses a few RESTful webservices in order to create resources, list them, change one,
	 * and delete them again. 
	 * 
	 * @param args command line arguments (no-op)
	 * @throws IOException if loading the property files fails 
	 */
	public static void main (final String[] args) 
		throws IOException 
	{
		System.out.println ("******************************* wetfeet.resteasy STARTED ... *************************************\n");

		// set up the resource URI 
		Properties properties = new Properties ();
		properties.load (ResteasyDemo.class.getClassLoader ().getResourceAsStream ("resteasy.properties") );
		
		String hostName     = properties.getProperty ("hostName");
		String portNr       = properties.getProperty ("portNr");
		String displayName  = properties.getProperty ("displayName");
		String resourcePath = properties.getProperty ("resourcePath");
		String baseURI      = MessageFormat.format ("http://{0}:{1}/{2}/{3}", hostName, portNr, displayName, resourcePath);

		
		// create a client object
		HttpClient httpClient = new DefaultHttpClient ();

		System.out.println ("resource URI: " + baseURI);

		// let's start with creating resources
		System.out.println ("\nadding a name");
		HttpPost post = new HttpPost (baseURI + "?name=Ben");		
		post.setHeader ("Accept", "application/json");
		post.setHeader ("Content-Type", "application/x-www-form-urlencoded");

		HttpResponse resp = httpClient.execute (post);

		printResponse  (resp);		
		printAllNames (httpClient, baseURI);		


		System.out.println ("\nadding another name");		
		post = new HttpPost (baseURI + "?name=jerry");
		resp = httpClient.execute (post);

		printResponse  (resp);		
		printAllNames (httpClient, baseURI);		


		// change a resource
		System.out.println ("\nChanging a name");
		HttpPut put = new HttpPut (baseURI + "/jerry?newName=Jerry");
		resp = httpClient.execute (put);

		printResponse  (resp);
		printAllNames (httpClient, baseURI);


		// and clean up resources again
		System.out.println ("\nDeleting the names again ");
		HttpDelete delete = new HttpDelete (baseURI + "/Ben");
		resp = httpClient.execute (delete);		
		printResponse  (resp);


		delete = new HttpDelete (baseURI + "/Jerry");
		resp = httpClient.execute (delete);		

		printResponse  (resp);
		printAllNames (httpClient, baseURI);

		System.out.println ("\n******************************* wetfeet.resteasy DONE ... *************************************");
	}


	/**
	 * Gets all the names and prints them to the console.
	 * 
	 * @param httpClient the http client object
	 * @param baseURI the baseURI to use
	 * @throws ClientProtocolException if http access fails
	 * @throws IOException if an IO operation fails
	 */
	private static void printAllNames (final HttpClient httpClient, final String baseURI) 
		throws ClientProtocolException, IOException 
	{
		System.out.println ("\ngetting all names");
		HttpGet get = new HttpGet (baseURI);
		HttpResponse resp = httpClient.execute (get);		
		printResponse  (resp);
	}


	/**
	 * Prints the response code and content (if there is any) to the console.
	 * 
	 * @param response the http response object
	 * @throws IOException if an IO operation fails
	 */
	private static void printResponse (final HttpResponse response) 
		throws IOException 
	{
		int responseCode      = response.getStatusLine ().getStatusCode ();

		System.out.println ("response code: " + responseCode);

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
					System.out.println (line);
				}
			} finally {
				br.close ();
				isr.close ();
			}
		}
	}

}

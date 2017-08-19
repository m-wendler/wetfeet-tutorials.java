package wetfeet.rest.wink.api.rest.v1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


/**
 * Implements the {@link INames} REST resource.
 * 
 * @author Mike
 */
public class Names 
	implements INames 
{
		
	/** the list to store names */
	private static final List <String> names = new ArrayList <String> ();
	
	/**
	 * @see wetfeet.rest.INames#getAll()
	 */
	public Response getAll ()  {
		StringBuilder json = new StringBuilder ("[");
		
		Iterator <String> it = names.iterator ();
		int count = 0;
		
		// collect all names into a JSON array
		while (it.hasNext () ) {
			if (count > 0) {
				json.append (", ");
			}
			json.append (it.next () );
			count++;
		}
		
		json.append ("]");
		
		return Response.ok (json.toString () ).build ();
	}
	
	/**
	 * @see wetfeet.rest.INames#get(java.lang.String)
	 */
	public Response get (final String id) {
		int index = names.indexOf (id);
		
		if (index > -1) {
			String name = names.get (index);
			
			if (null != name) {
				return Response.ok (String.format ("name: %s", name) ).build ();
			}
		}
		
		throw new WebApplicationException (Response.Status.NOT_FOUND);
	}
			
	/**
	 * @see wetfeet.rest.INames#rename(java.lang.String, java.lang.String)
	 */
	public Response rename (final String id, final String newName) {
		int index = names.indexOf (id);
		
		if (index > -1) {
			String name = names.get (index);
			
			if (null != name) {				
				System.out.println (String.format ("rename: %s to: %s", name, newName) );

				names.remove (index);
				names.add (newName);
				
				return Response.ok ().build ();
			} 
		}
		
		throw new WebApplicationException (Response.Status.NOT_FOUND);			
	}
	
	/**
	 * @see wetfeet.rest.INames#add(java.lang.String)
	 */
	public Response add (final String name) 
	{
		if (   null != name
			&& name.length () > 0) 
		{
			names.add (name);

			System.out.println ("add: " + name);
			
			// it's good practice to return the URI of the newly created resource ...
			// which I leave for you to do as an excercise! ;-)
			
			return Response.status (Response.Status.CREATED).build ();		
		} else {
			throw new WebApplicationException (Response.Status.BAD_REQUEST); 
		}
	}

	/**
	 * @see wetfeet.rest.INames#delete(java.lang.String)
	 */
	public Response delete (final String id) {
		System.out.println ("delete: " + id);

		int index = names.indexOf (id);
		
		if (index > -1) {
			names.remove (index);
			return Response.noContent ().build ();
		}

		throw new WebApplicationException (Response.Status.NOT_FOUND);
	}
		
}

package wetfeet.rest.wink.api.rest.v1;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * A collection of arbitrary names. 
 * Could be your favourite given names, say ...
 * True, this is a fairly contrived example but it keeps things really simple ... 
 *  
 * @author Mike Wendler
 */
@Path ("v1/names")
public interface INames {

	/**
	 * Gets all stored names.
	 * 
	 * @return a response object containing all necessary information 
	 */
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	Response getAll ();

	/**
	 * Gets this name.
	 * 
	 * @param name the sought name
	 * @return a response object containing all necessary information 
	 */
	@GET
	@Path("/{id}")	
	@Produces (MediaType.TEXT_PLAIN)
	Response get (@PathParam ("id") String id);

	/**
	 * Changes this name to the new name.
	 * 
	 * @param id the sought stored name
	 * @param newName the newName for the old name
	 * @return a response object containing all necessary information
	 */
	@PUT
	@Path ("/{id}")
	Response rename (@PathParam ("id") String id, @QueryParam ("newName") String newName);

	/**
	 * Adds this name to the collection.
	 * 
	 * @param name the new name to add
	 * @return @return a response object containing all necessary information
	 */
	@POST
	Response add (@QueryParam ("name") String name);

	/**
	 * Deletes this name from the collection.
	 * 
	 * @param id the name to delete
	 * @return a response object containing all necessary information
	 */
	@DELETE
	@Path ("/{id}")
	Response delete (@PathParam ("id") String id);

}
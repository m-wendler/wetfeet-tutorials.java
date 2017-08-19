package wetfeet.gson;

import com.google.gson.Gson;

/**
 * Demonstrates the use of gson. 
 * 
 * @author Mike Wendler
 */
public class GsonDemo {

	/**
	 * Instantiates a gson object to serialize and deserialize some primitive data 
	 * and an object.
	 * 
	 * @param args command line arguments
	 */
	public static void main (final String [] args) {
		System.out.println ("******************************* wetfeet.gson STARTED ... *************************************\n");

		// get a Gson object to serialize / deserialize values / objects
		Gson gson = new Gson ();
		
		// convert a few primitive data back and forth
		System.out.println ("serialize primitive data (to JSON) ...");
		System.out.println ("int:       " + gson.toJson (1) );
		System.out.println ("String:    " + gson.toJson ("two") );
		System.out.println ("int array: " + gson.toJson (new int [] {1, 2, 3}) );
		
		System.out.println ("\ndeserialize primitive data (from JSON) ...");
		System.out.println ("int:       " + gson.fromJson ("4", int.class) );
		System.out.println ("String:    " + gson.fromJson ("\"five\"", String.class) );
		System.out.println ("int array: " + gson.fromJson ("[4, 5, 6]", int[].class) );

	
		// create a new Person object
		Person user = new Person ("John Doe");					
		
		// convert this object to JSON
		String jsonStr = gson.toJson (user);
		System.out.println ("\nserialize an object (to JSON) ...\n" + jsonStr);
		
		// convert the JSON string back to an object
		Person user2 = gson.fromJson (jsonStr, Person.class);	
		System.out.println ("\ndeserialize an object (from JSON) ...");
		System.out.println (user2.getName () );

		// ensure the values are equal 
		assert user.getName ().equals (user2.getName () ) : "user names not equal!";
				
		System.out.println ("\n******************************* wetfeet.gson DONE ... *************************************");
	}
	
}

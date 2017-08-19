package wetfeet.gson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static wetfeet.gson.IntArraysMatcher.matchesArray;

import org.junit.Test;

import com.google.gson.Gson;

/**
 * Tests a few JSON conversions with Gson.
 * 
 * @author Mike Wendler
 */
public class GsonTest {

	/** the main Gson object to use for conversions */ 
	private static Gson gson = new Gson ();
	
	/** a simple domain object to transform */
	private static Person user = new Person ("John Doe");					

	/** a JSON comparison string */
	private static final String NAME_JOHN_DOE = "{\"name\":\"John Doe\"}";

	@Test
	public void convertPrimitives2Json () {	
		assertEquals ("1", gson.toJson (1) );
		assertEquals ("\"two\"", gson.toJson ("two") );
		assertEquals ("[1,2,3]", gson.toJson (new int [] {1, 2, 3}) );
	}
	
	@Test
	public void convertPrimitivesFromJson () {
		int four = gson.fromJson ("4", int.class);
		
		assertEquals (4, four);
		assertEquals ("five", gson.fromJson ("\"five\"", String.class) );
		
		int [] expectedArray  = new int [] {4,5,6};
		int [] convertedArray = gson.fromJson ("[4, 5, 6]", int[].class);
		
		assertThat (expectedArray, matchesArray (convertedArray) );   // uses our IntArraysMatcher
	}

	@Test
	public void convertObject2Json () {
		String jsonStr = gson.toJson (user);
		assertEquals (NAME_JOHN_DOE, jsonStr);
	}
	
	@Test
	public void convertObjectFromJson () {
		Person user2 = gson.fromJson (NAME_JOHN_DOE, Person.class);	
		assertEquals (user2.getName(), user.getName() );
	}

}




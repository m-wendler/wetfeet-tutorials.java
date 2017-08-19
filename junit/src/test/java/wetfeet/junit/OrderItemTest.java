package wetfeet.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Demonstrates the use of JUnit for unit testing.
 * Our 'class under test' (the main artefact we test) is {@link OrderItem}.
 * 
 * @author Mike Wendler
 */
public class OrderItemTest {

	/**
	 * Here is where to prepare for all the tests, e.g. 
     *  connecting to/setting up a (mock) objects ...
	 */
	@BeforeClass
	public static void setup () {}
	
	/**
	 * Demonstrates the testing of the creation of an {@link OrderItem} object.
	 */
	@Test
	public void testObjectCreation () {
		// create an object to test
		OrderItem objectUnderTest = new OrderItem (1);
		
		// assert we got a real object
		assertNotNull (objectUnderTest);
		
		// assert the actual product id is the value we passed in at creation
		assertEquals ("productID:", 1, objectUnderTest.getProductID () );	
		
		// assert that the default count is 1
		assertEquals ("default count:", 1, objectUnderTest.getCount () );
		
		// assert that the default price is 0.0
		assertEquals ("default price:", 0.0, objectUnderTest.getPrice (), 0.0);
	}
	
	/**
	 * Demonstrates the testing of setting value of an {@link OrderItem} object.
	 */
	@Test
	public void testValueSetting () {
		// create an object to test
		OrderItem objectUnderTest = new OrderItem (2);

		// assert setting the count works as expected
		objectUnderTest.setCount (2);
		assertEquals ("count:", 2, objectUnderTest.getCount () );
		
		// assert setting the price works as expected
		objectUnderTest.setPrice (0.99);
		assertEquals ("price: ", 0.99, objectUnderTest.getPrice (), 0.0);
		
		// assert calculating the total price works as expected
		assertEquals ("total: ", 1.98, objectUnderTest.getTotal (), 0.0);
	}

	/**
	 * Demonstrates the testing of throwing exceptions.
	 */
	@Test (expected=IllegalArgumentException.class)	
	public void testObjectCreationWithIllegalArguments () {
		OrderItem objectUnderTest = new OrderItem (3);
		
		// passing in a negative count should throw an exception
		objectUnderTest.setCount (-2);
	}

	/**
	 * Ignored test method. (comment out the '@Ignored' annotation to have this fail!)
	 */
	@Test
	@Ignore
	public void testNIY () {
		fail ("NYI");
	}
	
	/**
	 * Here is where to do things after all the tests were run, like
	 * releasing resources, closing connections, etc. ...
	 */
	@AfterClass
	public static void cleanup () {} 
	
}

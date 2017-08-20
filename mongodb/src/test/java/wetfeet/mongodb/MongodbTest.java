package wetfeet.mongodb;

import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import static org.junit.Assert.*;


/**
 * Tests a few MongoDB operations.
 * 
 * @author Mike Wendler
 */
public class MongodbTest {

	/** the name for the test collection */
	private static final String TEST_COLLECTION = "tagsTest";
	
	/** the mongo client  */
	private static MongoClient client;
	
	/** the mongo db */
	private static DB db;
	
	/** the collection to use for testing */
	private static DBCollection collection;

	/**
	 * Reads the mongo settings and starts a mongo client with them. 
	 * 
	 * @throws Exception if reading the properties file or connecting to mongo fails.
	 */
	@BeforeClass
	public static void setUpBeforeClass () 
		throws Exception 
	{
		Properties properties = new Properties ();
		properties.load (MongodbTest.class.getClassLoader ().getResourceAsStream ("mongodb.properties") );
		
		String dbHostName  = properties.getProperty ("dbHostName");
		String dbName      = properties.getProperty ("dbName");
		String dbPortNrStr = properties.getProperty ("dbPortNr");
		int dbPortNr       = Integer.parseInt (dbPortNrStr);

		// connect to the db server 
		client = new MongoClient (dbHostName, 
				                  dbPortNr);

		// connect to a database (may not exist yet)
		db = client.getDB (dbName);

		/** gets the collection to test */
		collection = db.createCollection (TEST_COLLECTION, null);
	}

	/**
	 * Cleans up any open cursors and the client.
	 * 
	 * @throws Exception if a mongo operation fails
	 */
	@AfterClass
	public static void tearDownAfterClass () 
		throws Exception 
	{
		// clean up resources
		db.cleanCursors (true);
		
		if (null != client) {
			client.close ();
		}	
	}

	/**
	 * Tests that a collection exists and is empty.
	 */
	@Test
	public void testCollection () {
		DBCollection tagsTest = db.getCollection (TEST_COLLECTION);
		assertNotNull (tagsTest);

		assertEquals (0L, collection.getCount () );		
	}
	
	/**
	 * Tests inserting a value into the test collection.
	 */
	@Test
	public void testInsert () {
		// ensure collection is empty
		assertEquals (0L, collection.getCount () );
		
		// now add an object
		BasicDBObject dbObject = new BasicDBObject ("given", "John");		
		assertNotNull (dbObject);
		collection.insert (dbObject);
		
		// ensure collection contains the added object
		assertEquals (1L, collection.getCount () );
		
		DBObject result = collection.findOne ();
		assertNotNull (result);
		Object value = result.get("given");
		assert value instanceof String;
		assertEquals ("John", ( (String) value) );
	}
	
	/**
	 * Tests finding a previously inserted value in the test collection.
	 */
	@Test
	public void testFind () {
		// add an object
		BasicDBObject dbObject = new BasicDBObject ("given", "John");
		collection.insert(dbObject);
		
		// create query object
		BasicDBObject queryObj = new BasicDBObject ("given", "John");
		DBCursor cursor = collection.find (queryObj);
				
		try {
			// cursor should reference exactly the one added object
			assertTrue (cursor.hasNext () );
			DBObject result = cursor.next ();
			Object value = result.get ("given");
			
			assert value instanceof String;
			assertEquals ("John", ( (String) value) );
			
			assertFalse (cursor.hasNext () );
		} finally {
			cursor.close ();
		}
		
	}

	/**
	 * Cleans up the test collection after each test. 
	 */
	@After
	public void cleanup () {
		DBCursor cursor = collection.find ();
		try {			
			while (cursor.hasNext () ) {
				DBObject obj = cursor.next ();
				collection.remove (obj);
			}
		} finally {
			cursor.close ();
		}
		
		assertEquals (0L, collection.getCount () );
	}
	
}

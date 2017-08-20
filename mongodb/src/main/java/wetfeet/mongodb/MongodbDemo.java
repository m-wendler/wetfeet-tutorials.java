package wetfeet.mongodb;

import java.io.IOException;
import java.util.Properties;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Demonstrates the programmatic access of mongodb. 
 * 
 * @author Mike Wendler
 */
public class MongodbDemo {

	/**
	 * Creates a database 'mongodbDemo', a 'persons' collection, inserts some data, queries and outputs the results and
	 * finally deletes the collection again.
	 * 
	 * @param args command line arguments (no-op)
	 * @throws IOException if the properties file could not be loaded or the mongo host not found
	 */
	public static void main (final String[] args) 
		throws IOException 
	{
		System.out.println ("******************************* wetfeet.mongodb STARTED ... *************************************\n");

		// obtain all the data we need for connecting to the db
		Properties properties = new Properties ();
		properties.load (MongodbDemo.class.getClassLoader ().getResourceAsStream ("mongodb.properties") );
		
		String dbHostName  = properties.getProperty ("dbHostName");
		String dbName      = properties.getProperty ("dbName");
		String dbPortNrStr = properties.getProperty ("dbPortNr");
		int dbPortNr       = Integer.parseInt (dbPortNrStr);
		
		MongoClient client = null;
		DB db              = null;

		try {
			// connect to the db server 
			client = new MongoClient (dbHostName, 
					                  dbPortNr);

			// connect to a database (may not exist yet)
			db = client.getDB (dbName);

			
			// create a collection
			System.out.println ("create 'persons' collection (equivalent to an SQL table) ...\n");
			DBCollection personsCollection = db.getCollection ("persons");

			// look Mum: 'no schema!' ;-)
			
			
			// insert some data
			System.out.println ("populate 'persons' collection ...");

			BasicDBObject dbObject = new BasicDBObject ("given", "John").append ("surname", "Doe");
			System.out.println (dbObject.toString () );
			personsCollection.insert (dbObject);

			dbObject = new BasicDBObject ("surname", "Jones");
			System.out.println (dbObject.toString () );
			personsCollection.insert (dbObject);

			
			// query the collection
			System.out.println("\nquery the 'persons' collection ...");

			DBCursor dbCursor = personsCollection.find ();
			while (dbCursor.hasNext () ) {
				DBObject resultObject = dbCursor.next ();
				System.out.println (resultObject);
			}

			
			// delete the table again
			System.out.println ("\ndelete 'persons' collection ...");
			personsCollection.drop ();

		} finally {
			// clean up resources
			db.cleanCursors (true);
			
			if (null != client) {
				client.close ();
			}
		}

		System.out.println ("\n******************************* wetfeet.mongodb DONE ... *************************************");
	}

}

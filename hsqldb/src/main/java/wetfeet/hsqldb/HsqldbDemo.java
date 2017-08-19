package wetfeet.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * Demonstrates the programmatic access of hsqldb by means of JDBC. 
 * 
 * @author Mike Wendler
 */
public class HsqldbDemo {

	/** the hsqldb driver name */
	private static final String db_driver   = "org.hsqldb.jdbcDriver";
	
	/** the sql string for creating the 'persons' table */
	private static final String CREATE_TABLE_SQL = "CREATE TABLE persons (id INT NOT NULL PRIMARY KEY, given varchar (50), surname varchar (50) NOT NULL);";
	
	
	/** the sql string template for inserting data into the 'persons' table */
	private static final String INSERT_SQL_TEMPLATE = "INSERT INTO persons ({0}) VALUES ({1}, {2}, {3});"; 
	
	/** the sql string for querying the 'persons' table */
	private static final String SELECT_SQL = "SELECT * FROM persons;";
	
	/** the sql string for deleting the 'persons' table */
	private static final String DROP_TABLE_SQL = "DROP TABLE persons;";


	/**
	 * Creates a database 'hsqldbDemo', a 'persons' table, inserts some data, queries and outputs the results and
	 * finally deletes the table again.
	 * This demo runs completely in-memory as this is the most simple setup.
	 * 
	 * @param args command line arguments (no-op)
	 * @throws Exception if the properties file could not be loaded or if a db operation fails
	 */
	public static void main (final String[] args) 
		throws Exception 
	{  
		System.out.println ("******************************* wetfeet.hsqldb STARTED ... *************************************\n");

		// obtain all the data we need for connecting to the db
		Properties properties = new Properties ();
		properties.load (HsqldbDemo.class.getClassLoader ().getResourceAsStream ("hsqldb.properties") );
		
		String dbName     = properties.getProperty ("dbName");
		String dbUrl      = properties.getProperty ("dbUrl");
		String dbUser     = properties.getProperty ("dbUser");  
		String dbPassword = properties.getProperty ("dbPassword");

		Connection connection = null;
		Statement statement   = null;

		try {				
			// initialize the driver explicitly		
			Class.forName (db_driver);
			
			// create the db connection string		
			String dbConnectionString = MessageFormat.format ("{0}:{1}", 
															  dbUrl,   
															  dbName);
	
			// connect to the db
			connection = DriverManager.getConnection (dbConnectionString,  
													  dbUser, 
													  dbPassword);    

		                                        

			if (null != connection) {
				// alrighty, let the fun begin ... 
				
				System.out.println ("got connection to hsqldb\n");
				
	
				// create a table
				System.out.println ("create 'persons' table ...\n");				
				executeStatement (statement = connection.createStatement (), 
						          CREATE_TABLE_SQL);   
	
				
				// insert some data
				// NOTE: in contrast to Postgres there is no 'serial feature': we have to calculate the id ourselves 
				System.out.println ("populate 'persons' table ...");				
	
				String sql = MessageFormat.format (INSERT_SQL_TEMPLATE, "id, surname, given",  1, "'Doe'", "'John'" );
				System.out.println (sql);
				executeStatement (statement = connection.createStatement (), 
						          sql);
				
				sql = MessageFormat.format (INSERT_SQL_TEMPLATE, "id, surname, given", 2, "'Jones'", null); 
				System.out.println (sql);
				executeStatement (statement = connection.createStatement (), 
								  sql);
	
				// query the table
				System.out.println ("\nquery the 'persons' table ...");
				queryStatement (statement = connection.createStatement (), 
						        SELECT_SQL);
	
				// delete the table again
				System.out.println ("\ndelete 'persons' table ...");
				executeStatement (statement = connection.createStatement (), 
						 		  DROP_TABLE_SQL);              
			}                                    			                                                                     
		} finally {             
			// clean up resources

			if (null != statement) {
				statement.close ();    // closes an associated result set implicitly
			}

			if (null != connection) {
				connection.close();
			}
		}                
		
		System.out.println ("\n******************************* wetfeet.hsqldb DONE ... *************************************");
	}                                                                    
		
	
	/**
	 * Executes a query with this query string and prints the result set.
	 * In case of an exception being thrown the client code should close the statement.
	 *  
	 * @param statement the passed in statement
	 * @param sql the sql query string
	 * @throws SQLException should the query fail for some reason
	 */
	private static void queryStatement (final Statement statement, final String sql) 
		throws SQLException 
	{
		ResultSet resultSet = statement.executeQuery (sql);
		
		while (resultSet.next () ) {
			System.out.println (MessageFormat.format ("> {0}, {1}, {2}", 
								resultSet.getInt ("id"), 
								resultSet.getString ("given"), 
								resultSet.getString ("surname") ) );
		}

		// clean up resources 
		if (null != resultSet) {
			resultSet.close ();
		}
		if (null != statement) {
			statement.close ();
		}
	}

	
	/**
	 * Executes a statement with this sql string.
	 * In case of an exception being thrown the client code should close the statement.
	 *  
	 * @param statement the passed in statement
	 * @param sql the sql string
	 * @throws SQLException should the query fail for some reason
	 */
	protected static void executeStatement (final Statement statement, final String sql) 
		throws SQLException 
	{
		statement.execute (sql);  
				
		if (null != statement) {
			statement.close ();
		}
	}

}                                                                    

package wetfeet.postgres;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * Demonstrates the programmatic access of postgres by means of JDBC.
 *
 * @author Mike Wendler
 */
public class PostgresDemo {

	/** the postgres driver name */
	private static final String db_driver   = "org.postgresql.Driver";

	/** the sql string for creating the 'persons' table */
	private static final String CREATE_TABLE_SQL = "CREATE TABLE persons ( id serial NOT NULL, given character varying (50), surname character varying (50) NOT NULL, PRIMARY KEY (id) );";

	/** the sql string template for inserting data into the 'persons' table */
	private static final String INSERT_SQL_TEMPLATE = "INSERT INTO persons ({0}) VALUES ({1}, {2});";

	/** the sql string for querying the 'persons' table */
	private static final String SELECT_SQL = "SELECT * FROM persons;";

	/** the sql string for deleting the 'persons' table */
	private static final String DROP_TABLE_SQL = "DROP TABLE persons;";

	/**
	 * Creates a 'persons' table, inserts some data, queries and outputs the results and
	 * finally deletes the table again.
	 *
	 * @param args command line arguments (no-op)
	 * @throws SQLException if a db operation fails
	 * @throws IOException if loading the properties file fails
	 * @throws ClassNotFoundException if the JDBC driver class could not be found
	 */
	public static void main (final String[] args)
		throws SQLException, IOException, ClassNotFoundException
	{
		System.out.println ("******************************* wetfeet.postgres STARTED ... *************************************\n");

		// obtain all the data we need for connecting to the db
		Properties properties = new Properties ();
		properties.load (PostgresDemo.class.getClassLoader ().getResourceAsStream ("postgres.properties") );

		String db_url      = properties.getProperty ("dbUrl");
		String db_port     = properties.getProperty ("dbPort");
		String db_user     = properties.getProperty ("dbUser");
		String db_password = properties.getProperty ("dbPassword");

		Connection conn = null;
		Statement statement = null;

		try {
			// initialize the driver explicitly
			Class.forName (db_driver);

			// create the db connection string
			String dbConnectionString = MessageFormat.format ("{0}:{1}",
													 		  db_url,
													 		  db_port);

			// connect to the db
			conn = DriverManager.getConnection (dbConnectionString,
 		                                        db_user,
 		                                        db_password);

			// alrighty, let the fun begin ...
			if (null != conn) {
				System.out.println ("got connection to postgres\n");


				// create a table
				System.out.println ("create 'persons' table ...\n");
				executeStatement (statement = conn.createStatement (),
						          CREATE_TABLE_SQL);


				// insert some data
				System.out.println ("populate 'persons' table ...");

				String sql = MessageFormat.format (INSERT_SQL_TEMPLATE, "surname, given",  "'Doe'", "'John'" );
				System.out.println (sql);
				executeStatement (statement = conn.createStatement (),
						          sql);

				sql = MessageFormat.format (INSERT_SQL_TEMPLATE, "surname, given", "'Jones'", null);
				System.out.println (sql);
				executeStatement (statement = conn.createStatement (),
								  sql);


				// query the table
				System.out.println ("\nquery the 'persons' table ...");
				queryStatement (statement = conn.createStatement (),
						        SELECT_SQL);

				// delete the table again
				System.out.println ("\ndelete 'persons' table ...");
				executeStatement (statement = conn.createStatement (),
						 		  DROP_TABLE_SQL);
			}

		} finally {
			// clean up resources

			if (null != statement) {
				statement.close ();    // closes an associated result set implicitly
			}

			if (null != conn) {
				conn.close();
			}
		}

		System.out.println ("\n******************************* wetfeet.postgres DONE ... *************************************");
	}

	/**
	 * Executes a query with this query string prints the result set.
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

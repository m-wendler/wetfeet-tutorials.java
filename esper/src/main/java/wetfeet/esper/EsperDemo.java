package wetfeet.esper;

import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * Demonstrates the use of Esper.
 *  
 * Imagine having an elevator designed to carry 12 people at the max. 
 * A sensor counts the number of users concurrently in there and 
 * outputs an alarm if there are too many.
 * 
 * @author Mike Wendler
 */
public class EsperDemo {

	/**
	 * Sets up an Esper service with statement and runs some data
	 * through it. 
	 * 
	 * @param args command line arguments (no-op)
	 */
	public static void main (final String[] args) {
		System.out.println ("******************************* wetfeet.esper STARTED ... *************************************\n");

		// set up esper 
		EPServiceProvider serviceProvider = EPServiceProviderManager.getDefaultProvider (); 
		EPRuntime runtime                 = serviceProvider.getEPRuntime ();


		// create a demo statement: filter user counts exceeding 20
		String statementStr   = "select count from wetfeet.esper.UserCount having count > 12";
		EPStatement statement = serviceProvider.getEPAdministrator ().createEPL (statementStr);

		
		// add a listener to the statement which does something once a piece of data fulfils the condition we are looking for
		System.out.println (String.format ("\nthis is what we look for in incoming events: \n%s\n", statementStr) );
		statement.addListener (new UserCountExceededListener () );


		// simulate a stream of user count data and pass it to the esper runtime
		UserCount userCount;
		for (int i = 1; i < 20; i++) {
			// create a current user count object
			userCount = new UserCount (i);
			System.out.println (String.format ("current user count: %d", userCount.getCount () ) ); 
			
			// pass this piece of data to the esper runtime which will pass it on to all statements
			runtime.sendEvent (userCount);
		}

		System.out.println ("\n******************************* wetfeet.esper DONE ... *************************************");
	}


	/**
	 * A listener which gets notified when the maximum number of users is exceeded.
	 */
	static class UserCountExceededListener
		implements UpdateListener
	{

		/** 
		 * Is being notified when the statement's condition is true for the incoming data.
		 */
		public void update (final EventBean [] newEvents, final EventBean [] oldEvents) {
			EventBean bean = newEvents [0];
			Object count   = bean.get ("count");
		
			System.out.println ("SAFETY ALERT: max allowed number of users exceeded! " + count);
		}

	}

}


/**
 * A 'value object' representing the number of concurrent users. 
 */
class UserCount {
	
	/** the number of concurrent users */
	private int count;

	/**
	 * Creates an object of this class and initializes it with the 
	 * current number of concurrent users.
	 * 
	 * @param count the number of concurrent users
	 */
	public UserCount (final int count) {
		super ();
		this.count = count;
	}

	/**
	 * @return the number of concurrent users
	 */
	public int getCount () {
		return count;
	}

}

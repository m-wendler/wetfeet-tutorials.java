package wetfeet.slf4j;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates the use of the SLF4J api. 
 * 
 * @author Mike Wendler
 */
public class SLF4Jdemo {

	/**
	 * Instantiates a logger object and logs a few messages on different levels.
	 * 
	 * @param args command line arguments
	 */
	public static void main (final String[] args) {
		System.out.println ("******************************* wetfeet.slf4j STARTED ... *************************************\n");

		// get a logger object
		Logger logger = LoggerFactory.getLogger (SLF4Jdemo.class);
		
		// start with a simple info message
		logger.info ("welcome to the party, SLF4J!");
		
		// use placeholders to pass in some values 
		logger.warn ("today is {} ", new Date () );
				
		// log an exception
		try {
			@SuppressWarnings("unused")
			int divisionResult = 1/0;
		} catch (final Throwable throwable) {
			logger.error ("dividing by 0 is still no good idea! \n", throwable);
		}
		
		System.out.println ("\n******************************* wetfeet.slf4j DONE ... *************************************");
	}

}

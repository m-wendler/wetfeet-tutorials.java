package wetfeet.guice;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Demonstrates how to use Guice for injecting dependencies.
 * 
 * @author Mike Wendler
 */
public class GuiceDemo {

	/**
	 * Creates a Guice injector to start the injection process
	 * and prints the text of an injected message.
	 * 
	 * @param args command line arguments (no-op)
	 */
	public static void main (final String[] args) {
		System.out.println ("******************************* wetfeet.guice STARTED ... *************************************\n");
		
		
		// create injector with this module
		Injector injector = Guice.createInjector (new GuiceDemoModule () );
		
		// let the injector create an instance of this class -> will inject all dependent objects as well
		GuiceDemo demoObject = injector.getInstance (GuiceDemo.class);
		
		// now run the demo
		demoObject.printMessageText ();
		
		
		System.out.println ("\n******************************* wetfeet.guice DONE ... *************************************");
	}

	
	/** which message will be injected here is determined in the module */
	@Inject
	IMessage message;
	
	/**
	 * Prints the text of the injected message.
	 */
	void printMessageText () {
		System.out.println ("message: " + this.message.getText () );
	}
	
}

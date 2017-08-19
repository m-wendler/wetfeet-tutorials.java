package wetfeet.guice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * Tests that dependencies are injected correctly.
 * Also uses a different module to demonstrate how to 
 * supply a different implementation for testing purposes.
 * 
 * @author Mike Wendler
 */
public class GuiceTest {

	/**
	 * Tests that we get the test message declared in the test module.
	 */
	@Test
	public void test () {
		Injector injector = Guice.createInjector (new GuiceTestModule () );				
		IMessage message  = injector.getInstance (IMessage.class);

		assertTrue (message instanceof TestMessage);
		assertEquals ("Guice Test Message", message.getText () );
	}

}

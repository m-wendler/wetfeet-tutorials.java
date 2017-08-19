package wetfeet.guice;

/**
 * The module for declaring our test dependencies.
 * Extends the original module for the sake of example. 
 * This way we can change some bindings for test purposes and still 
 * reuse other bindings. 
 * 
 * @author Mike Wendler
 */
public class GuiceTestModule 
	extends GuiceDemoModule 
{

	/**
	 * Overridden to bind a different implementation to {@link IMessage}.
	 */
	@Override
	protected void configure() {
		bind (IMessage.class).to (TestMessage.class);
	}	
	
}

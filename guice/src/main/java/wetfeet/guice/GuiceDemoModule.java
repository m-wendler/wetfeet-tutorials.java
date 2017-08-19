package wetfeet.guice;

import com.google.inject.AbstractModule;

/**
 * The module for declaring our dependencies.
 * 
 * @author Mike Wendler
 */
public class GuiceDemoModule 
	extends AbstractModule
{

	/** 
	 * Configures the bindings for the demo.
	 */
	@Override
	protected void configure () {
		// this is not much more than instance creation
		bind (GuiceDemo.class);
		
		// wherever an 'IMessage' is requested, use 'DefaultMessage' as the concrete implementation
		bind (IMessage.class).to (DefaultMessage.class);		
	}

}

package wetfeet.guice;

/**
 * The test implementation for {@link IMessage}
 * 
 * @author Mike Wendler
 */
public class TestMessage
	implements IMessage
{

	/**
	 * @see {@link IMessage#getText()}
	 */
	public String getText () {
		return "Guice Test Message";
	}	

}

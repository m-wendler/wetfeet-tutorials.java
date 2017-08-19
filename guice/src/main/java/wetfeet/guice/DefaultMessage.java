package wetfeet.guice;

/**
 * The default implementation for {@link IMessage}
 * 
 * @author Mike Wendler
 */
public class DefaultMessage 
	implements IMessage 
{

	/**
	 * @see {@link IMessage#getText()}
	 */
	public String getText () {
		return "Hello, Guice!";
	}
	
}

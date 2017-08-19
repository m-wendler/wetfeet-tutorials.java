package wetfeet.guice;


/**
 * A simple String message.
 * (Also an incarnation of a 'Value Object').
 * 
 * @author Mike Wendler
 */
public interface IMessage {

	/** 
	 * @return the message text
	 */
	public abstract String getText ();

}
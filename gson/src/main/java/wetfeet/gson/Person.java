package wetfeet.gson;

/**
 * A demo class with a name.
 * 
 * @author Mike Wendler
 */
public class Person {
	
	/** the person's name */
	String name;
	
	/**
	 * Constructs an object of this class and initializes it with this name.
	 * 
	 * @param name the name of this person
	 */
	public Person (final String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the person's name.
	 */
	public String getName () {
		return name;
	}
		
}

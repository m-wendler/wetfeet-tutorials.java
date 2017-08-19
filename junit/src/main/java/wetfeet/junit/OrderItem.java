package wetfeet.junit;

/**
 * A simple domain class for demonstrating unit testing with JUnit.
 * Its fairly contrived as its real value lies in its testability.
 * (bottom line: this is NOT a real-world example, and I leave it to 
 * you as an exercise to make it one! ;-)
 * 
 * @author Mike Wendler
 */
public class OrderItem {

	/** the id of a referenced product */
	private int productID = 0;

	/** the number of items to be ordered */
	private int count = 1;
	
	/** the price of the reference product */
	private double price = 0.0;

	/**
	 * Creates an object of this class and initializes it with this product id.
	 * 
	 * @param productID the id of a referenced product
	 */
	public OrderItem (final int productID) {
		super();
		
		this.productID = productID;
	}

	/**
	 * @return the id of a referenced product.
	 */
	public int getProductID () {
		return productID;
	}

	/**
	 * @return the number of items to be ordered.
	 */
	public int getCount () {
		return count;
	}

	/**
	 * @return the price of the reference product
	 */
	public double getPrice () {
		return price;
	}

	/**
	 * Sets the number of items to be ordered.
	 * 
	 * @param newCount the new number of items to be ordered
	 * @throws IllegalArgumentException if passing in a negative number or '0'
	 */
	public void setCount (final int newCount) {
		if (newCount < 1) {
			throw new IllegalArgumentException ("new count value must be a positive number");
		}
		
		count = newCount;
	}

	/**
	 * Sets the price of the reference product.
	 * 
	 * @param newPrice the new price of the reference product
	 */
	public void setPrice (final double newPrice) {
		price = newPrice;
	}

	/**
	 * @return the calculated total price for the number of ordered items.
	 */
	public double getTotal () {
		return getCount () * getPrice ();
	}		
	
}

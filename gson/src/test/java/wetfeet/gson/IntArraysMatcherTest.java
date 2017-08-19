package wetfeet.gson;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static wetfeet.gson.IntArraysMatcher.matchesArray;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;


/**
 * Tests the int array matcher.
 * 
 * @author Mike Wendler
 */
public class IntArraysMatcherTest {

	@Test
	public void matchingArrays () {
		assertThat (new int [] {},      matchesArray (new int [] {}) );
		assertThat (new int [] {1},     matchesArray (new int [] {1}) );
		assertThat (new int [] {1,2,3}, matchesArray (new int [] {1,2,3}) );		
	}

	@Test 
	public void differentLengths () {
		assertThat (new int [] {1}, not (matchesArray (new int [] {1,2}) ) );
		assertThat (new int [] {4,5,6}, not (matchesArray (new int [] {3,0}) ) );
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void initMatcherWithNullArray () {
		assertThat (new int [] {1}, matchesArray (null) );		
	}
	
}


/**
 * Checks whether 2 int arrays match. I.e. if their number of entries is 
 * the same and the contain the same int values in the same order.
 * 
 * @author Mike Wendler
 */
class IntArraysMatcher extends TypeSafeMatcher <int []> {

	/** the int array to compare other arrays with */
	private int [] expectedArray;
		
	/**
	 * Creates a matcher with this expected array.
	 * 
	 * @param expectedArray the expected array
	 */
	public IntArraysMatcher (final int[] expectedArray) {
		super ();
		
		if (null == expectedArray) {
			throw new IllegalArgumentException ("null values not allowed");
		}
		
		this.expectedArray = expectedArray;
	}

	/**
	 * Describes error cases.
	 * 
	 * @param description the description message to extend
	 */
	public void describeTo (final Description description) {
		description.appendText ("a valid (non-null) int array");
	}

	/**
	 * @param array2compare2 the int array to compare to the {{@link #expectedArray}
	 * @return <code>true</code> if this array matches the {{@link #expectedArray},
	 *   <code>false</code> otherwise.
	 */
	@Override
	protected boolean matchesSafely (final int[] array2compare2) {
		return matches (expectedArray, array2compare2);
	}
	
	/**
	 * 
	 * @param expectedArray {{@link #expectedArray}
	 * @return a newly created matcher with this expected array
	 */
	@Factory
	public static <T> Matcher <int []> matchesArray (final int [] expectedArray) {
		return new IntArraysMatcher (expectedArray);
	}
	
	/**
	 * @param expectedArray the {{@link #expectedArray}
	 * @param array the second array to compare
	 * @return <code>true</code> if both arrays match, i.e. they contain the 
	 *   same int values in the same order, <code>false</code> otherwise
	 */
	private boolean matches (final int[] expectedArray, final int[] array) {
		if (null != expectedArray && null != array) {
			boolean result = true;
			if (expectedArray.length == array.length) {
				for (int i = 0; i < expectedArray.length; i++) {
					if (expectedArray [i] != array [i]) {
						result = false;
						break;
					}
				}
				return result;
			}
		}
		
		return false;
	}

}

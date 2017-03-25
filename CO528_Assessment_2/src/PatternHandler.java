/**
 * 
 */

/**
 * @author Luke Carroll (lcc29)
 *
 */
public class PatternHandler {

	/**
	 * Creates the PatternHandler object.
	 */
	public PatternHandler() {

	}

	/**
	 * Splits a pattern which comes as a string into an array of integers
	 * 
	 * @param input The input Pattern as a string
	 * @return The pattern as an array of integers
	 */
	public int[] splitPattern(String input) {
		String[] strArray = input.split(" ");
		int[] intArray = new int[strArray.length];
		for (int i = 0; i < strArray.length; i++) {
			intArray[i] = Integer.parseInt(strArray[i]);
		}
		return intArray;
	}

}

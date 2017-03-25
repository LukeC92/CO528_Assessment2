/**
 * 
 */

/**
 * @author Luke Carroll (lcc29)
 *
 */
public class Hopf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(splitPattern("1 2 3")[0]);
		System.out.println(splitPattern("1 2 3")[1]);
		System.out.println(splitPattern("-1 1 1")[0]);
		System.out.println("1 2 3".split(" ")[0]);
		

	}

	/**
	 * Splits a pattern which comes as a string into an array of integers
	 * @param input The input Pattern as a string
	 * @return The pattern as an array of integers
	 */
	private static int[] splitPattern(String input) {
		String[] strArray = input.split(" ");
		int[] intArray = new int[strArray.length];
		for (int i = 0; i < strArray.length; i++) {
			intArray[i] = Integer.parseInt(strArray[i]);
		}
		return intArray;
	}
	
	
	

}

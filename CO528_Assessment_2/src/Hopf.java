import java.util.Arrays;

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

		int mat[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(wIJCalc(0, 0, mat)); // 0
		System.out.println(wIJCalc(0, 1, mat)); // 78/3=26
		System.out.println(wIJCalc(0, 2, mat)); // 90/3=30
		System.out.println(wIJCalc(1, 0, mat)); // 78/3=26
		System.out.println(wIJCalc(1, 1, mat)); // 0
		System.out.println(wIJCalc(1, 2, mat)); // 108/3=36
		System.out.println(wIJCalc(2, 0, mat)); // 90/3=30
		System.out.println(wIJCalc(2, 1, mat)); // 108/3=36
		System.out.println(wIJCalc(2, 2, mat)); // 0
		int mat2[][] = { { 1, 2, 2 }, { 3, 4, 8 }, { 20, 1, 7 } };
		System.out.println(wIJCalc(0, 1, mat2)); // 11.33333

		String stored[] = { "1 2 3", "4 5 6", "7 8 9" };
		float weight[][] = wArray(stored);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println(weight[i][j]);
			}
		}
		// should output the same as above

		int corrupted[] = { 10, 20, 30 };
		float aList[] = aCalc(weight, corrupted);
		for (int i = 0; i < 3; i++) {
			System.out.println(aList[i]);
		}
		aList.toString();

		String bill[] = { "1 -1 -1 1", "1 -1 1 1", "-1 1 -1 1" };
		float ben[][] = wArray(bill);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println(ben[i][j]);
			}
		}
		
		
		String trainers[] = {"1 1 1", "1 -1 1"};
		float weighttest[][] = wArray(trainers);
		int corrupttest[] = {1, -1, 1};
		int fixed[] = updateX( weighttest, corrupttest);
		for (int i = 0; i < 3; i++) {
				System.out.println(fixed[i]);
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println(weighttest[i][j]);
			}
		}

	}

	/**
	 * Splits a pattern which comes as a string into an array of integers
	 * 
	 * @param input
	 *            The input Pattern as a string
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

	private static float wIJCalc(int i, int j, int[][] stored) {
		int N = stored.length;
		int total = 0;
		if (i != j) {
			for (int n = 0; n < N; n++) {
				total += stored[n][i] * stored[n][j];
			}
		}
		float result = (float) total / N;
		return result;
	}

	private static float[][] wArray(String[] input) {
		int N = input.length;
		int[][] strings = new int[input.length][];
		for (int n = 0; n < N; n++) {
			strings[n] = splitPattern(input[n]);
		}

		int top = splitPattern(input[0]).length;
		float wList[][] = new float[top][top];
		for (int i = 0; i < top; i++) {
			for (int j = 0; j < top; j++) {
				wList[i][j] = wIJCalc(i, j, strings);
			}
		}
		float dummy[][] = { { 1, 2, 3 }, { 4, 5, 6 } };
		return wList;
	}

	private static float aICalc(int i, float[][] weight, int[] corrupt) {
		int N = corrupt.length;
		int total = 0;
		for (int j = 0; j < N; j++) {
			total += weight[i][j] * corrupt[j];
		}
		return total;
	}
	

	private static int[] updateXi(int i, float[][] weight, int[] corrupt) {
		float a = aICalc(i, weight, corrupt);
		if (a >= 0) {
			corrupt[i] = 1;
		}
		else{
			corrupt[i] = -1;
		}
		return corrupt;
	}
	
	private static int[] updateX(float[][] weight, int[] corrupt){
		for(int i =0; i<corrupt.length; i++){
			corrupt = updateXi(i, weight, corrupt);
		}
		return corrupt;
	}
	
	private static int[][] updateAllX(float[][] weight, int[][] corrupted){
		for(int i =0; i<corrupted.length; i++){
			corrupted[i] = updateX(weight, corrupted[i]);
		}
		int dummy[][] = { { 1, 2, 3 }, { 4, 5, 6 } };
		return corrupted;
	}

	/**
	 * 
	 * @param weight
	 *            The double array of weights.
	 * @param corrupt
	 *            The corrupted pattern.
	 * @return The ai values of the corrupted patterns.
	 */
	private static float[] aCalc(float[][] weight, int[] corrupt) {
		int N = corrupt.length;
		float result[] = new float[N];
		for (int i = 0; i < N; i++) {
			result[i] = aICalc(i, weight, corrupt);
		}
		return result;
	}

}
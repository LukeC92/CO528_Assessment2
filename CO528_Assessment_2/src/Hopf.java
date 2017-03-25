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
		
		String stored[]={"1 2 3", "4 5 6", "7 8 9"};
		float dum[][] = wArray(stored);
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				System.out.println(dum[i][j]);
			}
		}
		//should output the same as above
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
		if(i!=j){
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
		for(int n=0; n<N; n++){
			strings[n]=splitPattern(input[n]);
		}
		
		int top = splitPattern(input[0]).length;
		float wList[][] = new float[top][top];
		for(int i = 0; i<top; i++){
			for(int j =0; j<top; j++){
				wList[i][j] = wIJCalc(i,j,strings);
			}
		}
		float dummy[][] = {{ 1, 2, 3 },{4,5,6}};
		return wList;
	}
}

//import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
//import java.lang.Exception;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 */

/**
 * @author Luke Carroll (lcc29)
 *
 */
public class hopf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * System.out.println(splitPattern("1 2 3")[0]);
		 * System.out.println(splitPattern("1 2 3")[1]);
		 * System.out.println(splitPattern("-1 1 1")[0]);
		 * System.out.println("1 2 3".split(" ")[0]);
		 * 
		 * int mat[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		 * System.out.println(wIJCalc(0, 0, mat)); // 0
		 * System.out.println(wIJCalc(0, 1, mat)); // 78/3=26
		 * System.out.println(wIJCalc(0, 2, mat)); // 90/3=30
		 * System.out.println(wIJCalc(1, 0, mat)); // 78/3=26
		 * System.out.println(wIJCalc(1, 1, mat)); // 0
		 * System.out.println(wIJCalc(1, 2, mat)); // 108/3=36
		 * System.out.println(wIJCalc(2, 0, mat)); // 90/3=30
		 * System.out.println(wIJCalc(2, 1, mat)); // 108/3=36
		 * System.out.println(wIJCalc(2, 2, mat)); // 0 int mat2[][] = { { 1, 2,
		 * 2 }, { 3, 4, 8 }, { 20, 1, 7 } }; System.out.println(wIJCalc(0, 1,
		 * mat2)); // 11.33333
		 * 
		 * String stored[] = { "1 2 3", "4 5 6", "7 8 9" }; double weight[][] =
		 * wArray(stored);
		 * 
		 * for (int i = 0; i < 3; i++) { for (int j = 0; j < 3; j++) {
		 * System.out.println(weight[i][j]); } } // should output the same as
		 * above
		 * 
		 * int corrupted[] = { 10, 20, 30 }; double aList[] = aCalc(weight,
		 * corrupted); for (int i = 0; i < 3; i++) {
		 * System.out.println(aList[i]); } aList.toString();
		 * 
		 * String bill[] = { "1 -1 -1 1", "1 -1 1 1", "-1 1 -1 1" }; double
		 * ben[][] = wArray(bill);
		 * 
		 * for (int i = 0; i < 4; i++) { for (int j = 0; j < 4; j++) {
		 * System.out.println(ben[i][j]); } }
		 * 
		 * System.out.println("Hello, " + args[0] + ".  Welcome to Java!!!");
		 * 
		 * String trainers[] = { "1 1 1", "1 -1 1" }; double weighttest[][] =
		 * wArray(trainers); int corrupttest[] = { 1, -1, 1 }; int fixed[] =
		 * updateX(weighttest, corrupttest); for (int i = 0; i < 3; i++) {
		 * System.out.println(fixed[i]); }
		 * 
		 * for (int k = 0; k < 3; k++) { for (int j = 0; j < 3; j++) {
		 * System.out.println(weighttest[k][j]); } }
		 */

		// int[][] storedIntegers = new int[][];

		/*
		ArrayList<int[]> storedIntegers = new ArrayList<int[]>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(args[0]));
			String line = reader.readLine();
			while (line != null) {
				// System.out.println(line);
				storedIntegers.add(splitPattern(line));
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// the specified file could not be found
		} catch (IOException e) {
			// something went wrong with reading or closing
		}
		*/

		
		ArrayList<int[]> storedIntegers = splitTxt(args[0]);
		ArrayList<int[]> corruptedIntegers = splitTxt(args[1]);
		
		double weight[][] = wArrayFromIntList(storedIntegers);
		
		
		
		ArrayList<int[]> updatedcorrupted = updateAllXList(weight, corruptedIntegers);
		printList(updatedcorrupted);
		
		
		
		
		/*
		Iterator<int[]> it = storedIntegers.iterator();
		while (it.hasNext()) {

			int pat[] = it.next();
			for (int i = 0; i < pat.length; i++) {
				System.out.print(pat[i]);
			}
			System.out.println();
		}
		*/
		
		int N = weight.length;
		for(int i = 0; i < N; i++){
			for(int j = 0; j<N; j++){
				System.out.print(weight[i][j]+ " ");
			}
			System.out.println();
		}

	}
	
	
	private static void printArray(int[] input){
		for(int i = 0; i<input.length; i++){
			System.out.print(input[i]+" ");
		}
		System.out.println();
	}
	
	
	private static void printList(ArrayList<int[]> list){
		Iterator<int[]> it = list.iterator();
		while (it.hasNext()){
			printArray(it.next());
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
	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException if the file does not exist.
	 * @throws IOException if something goes wrong with reading or closing.
	 */
	private static ArrayList<int[]> splitTxt(String file){
		ArrayList<int[]> storedIntegers = new ArrayList<int[]>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				// System.out.println(line);
				storedIntegers.add(splitPattern(line));
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// the specified file could not be found
		} catch (IOException e) {
			// something went wrong with reading or closing
		}
		return storedIntegers;
	}

	private static double wIJCalc(int i, int j, int[][] stored) {
		int N = stored.length;
		int total = 0;
		if (i != j) {
			for (int n = 0; n < N; n++) {
				total += stored[n][i] * stored[n][j];
			}
		}
		double result = (double) total / N;
		return result;
	}

	private static double wIJCalcFromList(int i, int j, ArrayList<int[]> integerlist) {
		int total = 0;
		if (i != j) {
			Iterator<int[]> it = integerlist.iterator();
			while (it.hasNext()) {
				int pat[] = it.next();
				total += pat[i] * pat[j];
			}
		}
		double result = (double) total / integerlist.size();
		return result;
	}

	private static double[][] wArrayFromIntList(ArrayList<int[]> integerlist) {

		int top = integerlist.get(0).length;
		double wArray[][] = new double[top][top];
		for (int i = 0; i < top; i++) {
			for (int j = 0; j < top; j++) {
				if(i!=j){
				wArray[i][j] = wIJCalcFromList(i, j, integerlist);
				}
				else{
					wArray[i][j] = 0;
				}
			}
		}
		return wArray;
	}

	private static double[][] wArray(String[] input) {
		int N = input.length;
		int[][] strings = new int[N][];
		for (int n = 0; n < N; n++) {
			strings[n] = splitPattern(input[n]);
		}

		int top = splitPattern(input[0]).length;
		double wList[][] = new double[top][top];
		for (int i = 0; i < top; i++) {
			for (int j = 0; j < top; j++) {
				wList[i][j] = wIJCalc(i, j, strings);
			}
		}
		double dummy[][] = { { 1, 2, 3 }, { 4, 5, 6 } };
		return wList;
	}

	private static double aICalc(int i, double[][] weight, int[] corrupt) {
		int N = corrupt.length;
		int total = 0;
		for (int j = 0; j < N; j++) {
			total += weight[i][j] * corrupt[j];
		}
		return total;
	}

	private static int[] updateXi(int i, double[][] weight, int[] corrupt) {
		double a = aICalc(i, weight, corrupt);
		if (a >= 0) {
			corrupt[i] = 1;
		} else {
			corrupt[i] = -1;
		}
		return corrupt;
	}
	
	private static int[] updateXRepeat(double[][] weight, int[] corrupt) {
		int[] output = updateX(weight, corrupt);
		if(corrupt==output){
			return output;
		}
		
		return updateXRepeat(weight, output);
	}	

	private static int[] updateX(double[][] weight, int[] corrupt) {
		for (int i = 0; i < corrupt.length; i++) {
			corrupt = updateXi(i, weight, corrupt);
		}
		return corrupt;
	}
	
	private static ArrayList<int[]> updateAllXList(double[][] weight, ArrayList<int[]> corruptlist) {
		ArrayList<int[]> updatedlist = new ArrayList<int[]>(); 
			Iterator<int[]> it = corruptlist.iterator();
			while (it.hasNext()) {
				int pat[] = it.next();
				updatedlist.add(updateXRepeat(weight, pat));
		}
		return updatedlist;
	}

	private static int[][] updateAllX(double[][] weight, int[][] corrupted) {
		for (int i = 0; i < corrupted.length; i++) {
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
	private static double[] aCalc(double[][] weight, int[] corrupt) {
		int N = corrupt.length;
		double result[] = new double[N];
		for (int i = 0; i < N; i++) {
			result[i] = aICalc(i, weight, corrupt);
		}
		return result;
	}

}
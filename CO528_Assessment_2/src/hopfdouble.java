
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
public class hopfdouble {


/*
	public static void main(String[] args) {
		
		 * System.out.println(splitPattern("1 2 3")[0]);
		 * System.out.println(splitPattern("1 2 3")[1]);
		 * System.out.println(splitPattern("-1 1 1")[0]);
		 * System.out.println("1 2 3".split(" ")[0]);
		 * 
		 * double mat[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		 * System.out.println(wIJCalc(0, 0, mat)); // 0
		 * System.out.println(wIJCalc(0, 1, mat)); // 78/3=26
		 * System.out.println(wIJCalc(0, 2, mat)); // 90/3=30
		 * System.out.println(wIJCalc(1, 0, mat)); // 78/3=26
		 * System.out.println(wIJCalc(1, 1, mat)); // 0
		 * System.out.println(wIJCalc(1, 2, mat)); // 108/3=36
		 * System.out.println(wIJCalc(2, 0, mat)); // 90/3=30
		 * System.out.println(wIJCalc(2, 1, mat)); // 108/3=36
		 * System.out.println(wIJCalc(2, 2, mat)); // 0 double mat2[][] = { { 1, 2,
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
		 * double corrupted[] = { 10, 20, 30 }; double aList[] = aCalc(weight,
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
		 * wArray(trainers); double corrupttest[] = { 1, -1, 1 }; double fixed[] =
		 * updateX(weighttest, corrupttest); for (int i = 0; i < 3; i++) {
		 * System.out.println(fixed[i]); }
		 * 
		 * for (int k = 0; k < 3; k++) { for (int j = 0; j < 3; j++) {
		 * System.out.println(weighttest[k][j]); } }
		 

		// double[][] storedIntegers = new double[][];

		
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
		

		
		ArrayList<double[]> storedIntegers = splitTxt(args[0]);
		ArrayList<double[]> corruptedIntegers = splitTxt(args[1]);
		
		double weight[][] = wArrayFromIntList(storedIntegers);
		
		
		
		ArrayList<double[]> updatedcorrupted = updateAllXList(weight, corruptedIntegers);
		printList(updatedcorrupted);
		
		
		
		
		
		
		
		Iterator<int[]> it = storedIntegers.iterator();
		while (it.hasNext()) {

			int pat[] = it.next();
			for (int i = 0; i < pat.length; i++) {
				System.out.print(pat[i]);
			}
			System.out.println();
		}
		
		
		
		int N = weight.length;
		for(int i = 0; i < N; i++){
			for(int j = 0; j<N; j++){
				System.out.print(weight[i][j]+ " ");
			}
			System.out.println();
		}
		
		

	}
	*/
	
	private static void printArray(double[] input){
		for(int i = 0; i<input.length; i++){
			System.out.print((int) input[i]+" ");
		}
		System.out.println();
	}
	
	
	private static void printList(ArrayList<double[]> list){
		Iterator<double[]> it = list.iterator();
		while (it.hasNext()){
			printArray(it.next());
		}
	}

	/**
	 * Splits a pattern which comes as a string into an array of doubles
	 * 
	 * @param input
	 *            The input Pattern as a string
	 * @return The pattern as an array of doubles
	 */
	private static double[] splitPattern(String input) {
		String[] strArray = input.split(" ");
		double[] doubleArray = new double[strArray.length];
		for (int i = 0; i < strArray.length; i++) {
			doubleArray[i] = Double.parseDouble(strArray[i]);
		}
		return doubleArray;
	}
	
	/**
	 * Takes a txt file of patterns and splits into an ArrayList of arrays of doubles.
	 * @param file the path to the input file
	 * @return the txt represented as an ArrayList of arrays of doubles
	 * @throws FileNotFoundException if the file does not exist.
	 * @throws IOException if something goes wrong with reading or closing.
	 */
	private static ArrayList<double[]> splitTxt(String file){
		ArrayList<double[]> storedIntegers = new ArrayList<double[]>();
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

	private static double wIJCalc(int i, int j, double[][] stored) {
		double N = stored.length;
		double total = 0;
		if (i != j) {
			for (int n = 0; n < N; n++) {
				total += stored[n][i] * stored[n][j];
			}
		}
		double result = (double) total / N;
		return result;
	}

	private static double wIJCalcFromList(int i, int j, ArrayList<double[]> integerlist) {
		double total = 0;
		if (i != j) {
			Iterator<double[]> it = integerlist.iterator();
			while (it.hasNext()) {
				double pat[] = it.next();
				total += pat[i] * pat[j];
			}
		}
		double N = (double) integerlist.size();
		double result = total/N;
		return result;
	}

	private static double[][] wArrayFromIntList(ArrayList<double[]> doubleegerlist) {

		int top = doubleegerlist.get(0).length;
		double wArray[][] = new double[top][top];
		for (int i = 0; i < top; i++) {
			for (int j = 0; j < top; j++) {
				if(i!=j){
				wArray[i][j] = wIJCalcFromList(i, j, doubleegerlist);
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
		double[][] strings = new double[N][];
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
		return wList;
	}

	private static double aICalc(int i, double[][] weight, double[] corrupt) {
		int N = corrupt.length;
		int total = 0;
		for (int j = 0; j < N; j++) {
			total += weight[i][j] * corrupt[j];
		}
		return total;
	}

	private static double[] updateXi(int i, double[][] weight, double[] corrupt) {
		double a = aICalc(i, weight, corrupt);
		if (a >= 0) {
			corrupt[i] = (double) 1;
		} else {
			corrupt[i] = (double) -1;
		}
		return corrupt;
	}
	
	private static double[] updateXRepeat(double[][] weight, double[] corrupt) {
		double[] output = updateX(weight, corrupt);
		if(corrupt.equals(output)){
			return output;
		}
		
		return updateXRepeat(weight, output);
	}	

	private static double[] updateX(double[][] weight, double[] corrupt) {
		for (int i = 0; i < corrupt.length; i++) {
			corrupt = updateXi(i, weight, corrupt);
			//printArray(corrupt);
		}
		return corrupt;
	}
	
	private static ArrayList<double[]> updateAllXList(double[][] weight, ArrayList<double[]> corruptlist) {
		ArrayList<double[]> updatedlist = new ArrayList<double[]>(); 
			Iterator<double[]> it = corruptlist.iterator();
			while (it.hasNext()) {
				double pat[] = it.next();
				updatedlist.add(updateXRepeat(weight, pat));
		}
		return updatedlist;
	}

	private static double[][] updateAllX(double[][] weight, double[][] corrupted) {
		for (int i = 0; i < corrupted.length; i++) {
			corrupted[i] = updateX(weight, corrupted[i]);
		}
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
	private static double[] aCalc(double[][] weight, double[] corrupt) {
		int N = corrupt.length;
		double result[] = new double[N];
		for (int i = 0; i < N; i++) {
			result[i] = aICalc(i, weight, corrupt);
		}
		return result;
	}

}
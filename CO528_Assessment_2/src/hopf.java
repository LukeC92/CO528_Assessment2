import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * 
 */

/**
 * @author Luke Carroll (lcc29)
 * @version 02/04/2017
 *
 */
public class hopf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Please enter 2 file paths.");
		} else {
			ArrayList<double[]> storedlist = splitTxt(args[0]);
			ArrayList<double[]> corruptedlist = splitTxt(args[1]);
			int length = storedlist.get(0).length;

			if (patternSizeCheck(storedlist, length) && patternSizeCheck(corruptedlist, length)) {
				if (isLearnable(storedlist)) {
					double weights[][] = wArrayFromIntList(storedlist);

					ArrayList<double[]> updatedcorrupted = updateAllXList(weights, corruptedlist);
					printList(updatedcorrupted);
				}
				else{
					System.out.println(0);
				}
			}

			/*
			 * int N = weights.length; for(int i = 0; i < N; i++){ for(int j =
			 * 0; j<N; j++){ System.out.print(weights[i][j]+ " "); }
			 * System.out.println(); }
			 */

			else {
				System.out.println("Please ensure all stored patterns and corrupted patterns are of the same length.");
			}

		}
	}

	private static boolean patternSizeCheck(ArrayList<double[]> patterns, int length) {
		int count = 0;
		Iterator<double[]> it = patterns.iterator();
		while (it.hasNext()) {
			double pat[] = it.next();
			if (pat.length != length) {
				count++;
			}
		}
		return count == 0;
	}

	private static boolean isLearnable(ArrayList<double[]> storedpatterns) {
		double ratio = (double) storedpatterns.size() / storedpatterns.get(0).length;
		return ratio < 0.138 | storedpatterns.size()==1;
	}

	private static void printArray(double[] input) {
		String arrayString = "";
		for (int i = 0; i < input.length; i++) {
			arrayString += String.valueOf((int) input[i]) + " ";
		}
		String result = arrayString.substring(0, arrayString.length() - 1);
		System.out.println(result);
	}

	private static void printList(ArrayList<double[]> list) {
		Iterator<double[]> it = list.iterator();
		while (it.hasNext()) {
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
	 * Takes a txt file of patterns and splits into an ArrayList of arrays of
	 * doubles.
	 * 
	 * @param file
	 *            the path to the input file
	 * @return the txt represented as an ArrayList of arrays of doubles
	 * @throws FileNotFoundException
	 *             if the file does not exist.
	 * @throws IOException
	 *             if something goes wrong with reading or closing.
	 */
	private static ArrayList<double[]> splitTxt(String file) {
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
			System.out.println("The specified file could not be found.");
		} catch (IOException e) {
			System.out.println("Something went wrong with reading or closing.");
		}
		return storedIntegers;
	}

	/**
	 * Calculates the weight between i and j nodes given the stored patterns.
	 * 
	 * @param i
	 * @param j
	 * @param storedlist
	 *            The ArrayList representing the stored patterns.
	 * @return The weight between i and j.
	 */
	private static double wIJCalcFromList(int i, int j, ArrayList<double[]> storedlist) {
		double total = 0;
		if (i != j) {
			Iterator<double[]> it = storedlist.iterator();
			while (it.hasNext()) {
				double pat[] = it.next();
				total += pat[i] * pat[j];
			}
		}
		double N = (double) storedlist.size();
		double result = total / N;
		return result;
	}

	/**
	 * Finds all the weight for the system given the stored patterns.
	 * 
	 * @param storedlist
	 *            The ArrayList representing the stored patterns.
	 * @return The weights as a double array of doubles.
	 */
	private static double[][] wArrayFromIntList(ArrayList<double[]> storedlist) {

		int top = storedlist.get(0).length;
		double wArray[][] = new double[top][top];
		for (int i = 0; i < top; i++) {
			for (int j = 0; j < top; j++) {
				wArray[i][j] = wIJCalcFromList(i, j, storedlist);
			}
		}
		return wArray;
	}

	private static double aICalc(int i, double[][] weight, double[] corrupt) {
		int N = corrupt.length;
		double total = 0;
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
		if (corrupt.equals(output)) {
			return output;
		}

		return updateXRepeat(weight, output);
	}

	private static double[] updateX(double[][] weight, double[] corrupt) {
		for (int i = 0; i < corrupt.length; i++) {
			corrupt = updateXi(i, weight, corrupt);
			// printArray(corrupt);
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

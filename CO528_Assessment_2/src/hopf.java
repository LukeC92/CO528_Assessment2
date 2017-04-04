import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Luke Carroll (lcc29)
 * @version 03/04/2017
 *
 */
public class hopf {

	/**
	 * Checks if the input is acceptable then learns weights from stored
	 * patterns using the Hebb rule then updates corrupted patterns until they
	 * reach a stable state and then prints them.
	 * 
	 * @param args
	 *            Should be the file directories to the txt files containing the
	 *            stored patterns and the corrupted patterns
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println(
					"Please enter 2 file directories; one to the txt file containing your stored patterns and the second to the txt file containing your corrupted patterns.");
		} else {
			ArrayList<double[]> storedlist = splitTxt(args[0]);
			ArrayList<double[]> corruptedlist = splitTxt(args[1]);
			int length = storedlist.get(0).length;

			if (patternSizeCheck(storedlist, length) && patternSizeCheck(corruptedlist, length)) {
				if (isLearnable(storedlist)) {
					double weights[][] = wArrayFromIntList(storedlist);

					ArrayList<double[]> updatedcorrupted = updateAllXList(weights, corruptedlist);
					printList(updatedcorrupted);
				} else {
					System.out.println(0);
				}
			} else {
				System.out.println("Please ensure all stored patterns and corrupted patterns are of the same length.");
			}

		}
	}

	/**
	 * Splits a pattern represented as a string into an array of doubles.
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
	 *            the director of the input file
	 * @return the txt file represented as an ArrayList of arrays of doubles
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
	 * Calculates the weight between nodes i and j nodes given the stored
	 * patterns.
	 * 
	 * @param i
	 *            the index of the ith node
	 * @param j
	 *            the index of the jth node
	 * @param storedlist
	 *            The ArrayList representing the stored patterns.
	 * @return The weight between nodes i and j.
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
		// if i=j then total will still be be 0
		double N = (double) storedlist.size();
		double result = total / N;
		return result;
	}

	/**
	 * Finds all the weight for the network given the stored patterns.
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

	/**
	 * Calculates the activation of the ith node given its current (corrupted)
	 * state.
	 * 
	 * @param i
	 *            the index of the ith node
	 * @param weight
	 *            the double array representing the network's weights
	 * @param corrupt
	 *            the corrupted patterns initial state
	 * @return the activation of the ith node
	 */
	private static double aICalc(int i, double[][] weight, double[] corrupt) {
		int N = corrupt.length;
		double total = 0;
		for (int j = 0; j < N; j++) {
			total += weight[i][j] * corrupt[j];
		}
		return total;
	}

	/**
	 * Updates the value of the ith node in a corrupted pattern.
	 * 
	 * @param i
	 *            the index of the ith node
	 * @param weight
	 *            the double array representing the network's weights
	 * @param corrupt
	 *            the double array representing the network's weights
	 * @return the corrupted pattern but with the ith node updated
	 */
	private static double[] updateXi(int i, double[][] weight, double[] corrupt) {
		double a = aICalc(i, weight, corrupt);
		if (a >= 0) {
			corrupt[i] = (double) 1;
		} else {
			corrupt[i] = (double) -1;
		}
		return corrupt;
	}

	/**
	 * Updates a whole corrupted pattern asynchronously.
	 * 
	 * @param weight
	 *            the double array representing the network's weights
	 * @param corrupt
	 *            the double array representing the network's weights
	 * @return the corrupted pattern following one full update
	 */
	private static double[] updateX(double[][] weight, double[] corrupt) {
		for (int i = 0; i < corrupt.length; i++) {
			corrupt = updateXi(i, weight, corrupt);
		}
		return corrupt;
	}

	/**
	 * Updates a corrupted pattern repeatedly until it reaches a stable state
	 * 
	 * @param weight
	 *            the double array representing the network's weights
	 * @param corrupt
	 *            the double array representing the network's weights
	 * @return
	 */
	private static double[] updateXRepeat(double[][] weight, double[] corrupt) {
		double[] output = updateX(weight, corrupt);
		if (corrupt.equals(output)) {
			return output;
		}
		return updateXRepeat(weight, output);
	}

	/**
	 * Updates all the corrupted patterns within the ArrayList
	 * 
	 * @param weight
	 *            the double array representing the network's weights
	 * @param corruptlist
	 *            the ArrayList of double arrays representing all the corrupted
	 *            patterns
	 * @return an ArrayList of double arrays representing the corrupted patterns
	 *         updated to a stable state.
	 */
	private static ArrayList<double[]> updateAllXList(double[][] weight, ArrayList<double[]> corruptlist) {
		ArrayList<double[]> updatedlist = new ArrayList<double[]>();
		Iterator<double[]> it = corruptlist.iterator();
		while (it.hasNext()) {
			double pat[] = it.next();
			updatedlist.add(updateXRepeat(weight, pat));
		}
		return updatedlist;
	}

	/**
	 * Checks that all the patterns in the ArrayList are of a given size.
	 * 
	 * @param patterns
	 *            the ArrayList of patterns to be checked.
	 * @param length
	 *            the length that all the patterns should conform to
	 * @return true if all the patterns are of the desired length, false
	 *         otherwise
	 */
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

	/**
	 * Checks if the stored patterns are learnable, using mu/N<0.138 or mu=1.
	 * 
	 * @param storedpatterns
	 *            The list of stored patterns.
	 * @return true if the patterns are learnable, false otherwise.
	 */
	private static boolean isLearnable(ArrayList<double[]> storedpatterns) {
		double ratio = (double) storedpatterns.size() / storedpatterns.get(0).length;
		return ratio < 0.138 | storedpatterns.size() == 1;
	}

	/**
	 * Prints a pattern (represented as an array of doubles) on a single line.
	 * 
	 * @param input
	 *            the array representation of the selected pattern
	 */
	private static void printArray(double[] input) {
		String arrayString = "";
		for (int i = 0; i < input.length; i++) {
			arrayString += String.valueOf((int) input[i]) + " ";
		}
		String result = arrayString.substring(0, arrayString.length() - 1);
		System.out.println(result);
	}

	/**
	 * Prints all of the patterns in the list with each pattern being printed on
	 * a single line
	 * 
	 * @param list
	 *            the list of patterns to be printed
	 */
	private static void printList(ArrayList<double[]> list) {
		Iterator<double[]> it = list.iterator();
		while (it.hasNext()) {
			printArray(it.next());
		}
	}
}

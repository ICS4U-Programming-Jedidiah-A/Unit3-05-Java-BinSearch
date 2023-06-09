import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
* This program calculates amount of mean median and mode.
*
* @author  Jedidiah Alfred
* @version 1.0
* @since   2023-05-13
*/

public final class RecBinSearch {
    /**
    * This is a private constructor used to satisfy the
    * style checker.
    *
    * @exception IllegalStateException Utility Class
    */
    private RecBinSearch() {
        throw new IllegalStateException("Utility Class");
    }
    /**
    * This is the main method.
    *
    * @param args Unused
    * @throws Exception if something goes wrong
    */

    public static void main(String[] args) throws Exception {
        // Initializing variables
        final String line;
        final String err = "Error";
        try {
            // New file object
            final File input = new File("input.txt");

            // Creating the writer
            final FileWriter output = new FileWriter("output.txt");

            try {
                // Creating the scanner.
                final Scanner scanner = new Scanner(input);

                // ArrayList to hold the lines
                final ArrayList<String> lines = new ArrayList<>();
                final ArrayList<String> binSearch = new ArrayList<>();

                // Getting input from the input file
                while (scanner.hasNextLine()) {
                    // Temp variable.
                    final String temp = scanner.nextLine();
                    if (temp.split(" ").length != 1) {

                        // Get next line and put it in the list
                        lines.add(temp);
                    } else {
                        binSearch.add(temp);
                    }
                }

                // taking the data and manipulating it with a function
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).length() == 0) {
                        // writing to file
                        output.write("ERROR: Empty line in one of the files\n");
                    } else {

                        final String[] numStr = lines.get(i).split(" ");
                        final int[] numberArr = new int[numStr.length];

                        for (int j = 0; j < numStr.length; j++) {
                            final int num = Integer.parseInt(numStr[j]);
                            numberArr[j] = num;
                        }
                        Arrays.sort(numberArr);
                        final int index = recBinSearch(
                                numberArr, Integer.parseInt(binSearch.get(i)
                                    .split(" ")[0]), 0, numberArr.length - 1);
                        if (index == -1) {
                            output.write("ERROR: " + binSearch.get(i)
                                + " does not exist \n");
                        } else {
                            output.write(binSearch.get(i) + " is at index "
                                + index + "\n");
                        }
                    }
                }
            } catch (IOException error) {
                System.out.println(err + error.getMessage());
            }
            // closes the writer
            output.close();
        } catch (IOException error) {
            System.out.println(err + error.getMessage());
        }
    }
    /**
    * This is the method reverseRecs the string.
    *
    * @param numberArr This is the array
    * @param num this is the number being searched
    * @param start this is the beginning of the array
    * @param end This is the array length
    * @return the powered value
    **/

    public static int recBinSearch(int[] numberArr, int num,
        int start, int end) {

        System.out.println(Arrays.toString(numberArr));

        // error checking
        if (start > end) {
            return -1;
        }

        // midpoint modification
        final int mid = (start + end) / 2;

        // comparing the midpoint to different parts of the array.
        if (numberArr[mid] == num) {
            System.out.println(numberArr[mid]);
            return mid;
        } else if (num < numberArr[mid]) {
            return recBinSearch(numberArr, num, start, mid - 1);
        } else {
            return recBinSearch(numberArr, num, mid + 1, end);
        }
    }
}

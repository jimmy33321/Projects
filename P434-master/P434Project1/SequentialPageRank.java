import java.io.*;
import java.util.*;

public class SequentialPageRank {
    // adjacency matrix read from file
    private HashMap<Integer, ArrayList<Integer>> adjMatrix = new HashMap<Integer, ArrayList<Integer>>();
    // input file name
    private String inputFile = "";
    // output file name
    private String outputFile = "";
    // number of iterations
    private int iterations = 10;
    // damping factor
    private double df = 0.85;
    // number of URLs
    private int size = 0;
    // calculating rank values
    private HashMap<Integer, Double> rankValues = new HashMap<Integer, Double>();

    /**
     * Parse the command line arguments and update the instance variables. Command line arguments are of the form
     * <input_file_name> <output_file_name> <num_iters> <damp_factor>
     *
     * @param args arguments
     */
    public void parseArgs(String[] args) {

    }

    /**
     * Read the input from the file and populate the adjacency matrix
     *
     * The input is of type
     *
     0
     1 2
     2 1
     3 0 1
     4 1 3 5
     5 1 4
     6 1 4
     7 1 4
     8 1 4
     9 4
     10 4
     * The first value in each line is a URL. Each value after the first value is the URLs referred by the first URL.
     * For example the page represented by the 0 URL doesn't refer any other URL. Page
     * represented by 1 refer the URL 2.
     *
     * @throws java.io.IOException if an error occurs
     */
    public void loadInput() throws IOException {

    }

    /**
     * Do fixed number of iterations and calculate the page rank values. You may keep the
     * intermediate page rank values in a hash table.
     */
    public void calculatePageRank() {

    }

    /**
     * Print the pagerank values. Before printing you should sort them according to decreasing order.
     * Print all the values to the output file. Print only the first 10 values to console.
     *
     * @throws IOException if an error occurs
     */
    public void printValues() throws IOException {

    }

    public static void main(String[] args) throws IOException {
        SequentialPageRank sequentialPR = new SequentialPageRank();

        sequentialPR.parseArgs(args);
        sequentialPR.loadInput();
        sequentialPR.calculatePageRank();
        sequentialPR.printValues();
    }
}

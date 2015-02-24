package edu.cmu.cmulib.API.data;

/**
 * a strategy to handle error in number of rows
 */
public interface NumRowsErrorStrategy {

    /**
     * handles the wrong number of rows in a given read
     * 
     * @param tokens
     *            a String matrix that stores the original tokens
     * @param numRows
     *            number of rows this read is supposed to contain
     * @param numRowsSeen
     *            number of rows this read actually contains
     * @return a String matrix containing the data with correct number of rows
     * 
     *         ensures: return.length == numRows
     */
    String[][] handleWrongNumRows(String[][] tokens, int numRows,
            int numRowsSeen);

}

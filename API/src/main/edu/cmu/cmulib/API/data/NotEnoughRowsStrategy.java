package edu.cmu.cmulib.API.data;

/**
 * a strategy to handle less rows than expected
 * 
 * @author Cambi
 */
public interface NotEnoughRowsStrategy {

    /**
     * handles the wrong number of rows in a given read.
     * 
     * Please refresh the target number of rows using return.length
     * 
     * @param tokens
     *            a String matrix that stores the original tokens
     * @param numRows
     *            number of rows this read is supposed to contain
     * @param numRowsSeen
     *            number of rows this read actually contains
     * @return a String matrix containing the data with correct number of rows
     * 
     */
    String[][] handleLackOfRows(String[][] tokens, int numRows,
            int numRowsSeen);

}

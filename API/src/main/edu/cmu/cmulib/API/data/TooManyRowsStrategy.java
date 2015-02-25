/**
 * 
 */
package edu.cmu.cmulib.API.data;

import java.io.BufferedReader;

/**
 * a strategy to handle more rows than expected
 * 
 * @author Cambi
 *
 */
public interface TooManyRowsStrategy {

    /**
     * handles the wrong number of rows in a given read.
     * 
     * Please refresh the target number of rows using return.length
     * 
     * @param tokens
     *            a String matrix that stores the original tokens
     * @param numRows
     *            number of rows this read is supposed to contain
     * @param additonalLine
     *            the additional line in the file after the last line read
     * @param br
     *            the buffered reader than can read additional content
     *            afterwards
     * @return a String matrix containing the data with correct number of rows
     * 
     */
    String[][] handleTooManyRows(String[][] tokens, int numRows,
            String additonalLine, BufferedReader br);

}

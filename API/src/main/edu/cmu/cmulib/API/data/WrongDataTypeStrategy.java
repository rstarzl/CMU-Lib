/**
 * 
 */
package edu.cmu.cmulib.API.data;

/**
 * a strategy to handle error in data type
 * 
 * @author Cambi
 */
public interface WrongDataTypeStrategy {

    /**
     * handles the wrong data type in the given row
     * 
     * @param matrix
     *            a String matrix that stores the original tokens
     * @param columnIndex
     *            the column index of the column to be handled
     * @param flags
     *            a boolean array indicating the corresponding data in tokens is
     *            valid
     */
    void handleWrongDataTypeInaColumn(String[][] matrix, int columnIndex,
            boolean[] flags);

    /**
     * @return the name of this strategy
     */
    String getName();

}

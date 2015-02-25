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
     * @param tokens
     *            a String array that stores the original tokens
     * @param flags
     *            a boolean array indicating the corresponding data in tokens is
     *            valid
     * @return a String array containing the data with correct datatype
     */
    String[] handleWrongDataTypeInaRow(String[] tokens, boolean[] flags);

}

/**
 * 
 */
package edu.cmu.cmulib.API.data;

/**
 * @author Cambi
 *
 */
public class EmptyWrongDataStrategy implements WrongDataTypeStrategy {

    @Override
    public String[][] handleWrongDataTypeInaColumn(String[][] matrix,
            int columnIndex, boolean[] flags) {
        return matrix;
    }

    @Override
    public String getName() {
        return "None";
    }
    
    @Override
    public String toString() {
        return this.getName();
    }

}

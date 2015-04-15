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
    public void handleWrongDataTypeInaColumn(String[][] matrix,
            int columnIndex, boolean[] flags) {
        return ;
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

/**
 * 
 */
package edu.cmu.cmulib.API.data;

import java.util.Random;

/**
 * @author Cambi
 *
 */
public class HotDeckStrategy implements WrongDataTypeStrategy {

    private final Double defaultValue;
    private static final Double DEFAULT_DEFAULT_VALUE = 0.0;

    /**
     * @param aDefaultValue
     *            the default value to set when there is no value in the column
     *            at all
     */
    public HotDeckStrategy(Double aDefaultValue) {
        this.defaultValue = aDefaultValue;
    }

    /**
     * default constructor
     */
    public HotDeckStrategy() {
        this.defaultValue = DEFAULT_DEFAULT_VALUE;
    }

    @Override
    public void handleWrongDataTypeInaColumn(String[][] matrix,
            int columnIndex, boolean[] flags) {
        for (int i = 0; i < matrix.length; i++) {
            if (!flags[i]) {
                if (i == 0) {
                    matrix[i][columnIndex] = Double.toString(this.defaultValue);
                } else {
                    Random rnd = new Random();
                    int j = rnd.nextInt(i);
                    matrix[i][columnIndex] = matrix[j][columnIndex];
                }
            }
        }
        return;
    }

    @Override
    public String getName() {
        return "Hot Deck";
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

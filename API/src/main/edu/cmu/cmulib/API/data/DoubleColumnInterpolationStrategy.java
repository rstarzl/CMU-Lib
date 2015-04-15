/**
 * 
 */
package edu.cmu.cmulib.API.data;

/**
 * @author Cambi
 *
 */
public class DoubleColumnInterpolationStrategy implements WrongDataTypeStrategy {

    private final Double defaultValue;
    private static final Double DEFAULT_DEFAULT_VALUE = 0.0;

    /**
     * @param aDefaultValue
     *            the default value to set when there is no value in the column
     *            at all
     */
    public DoubleColumnInterpolationStrategy(Double aDefaultValue) {
        this.defaultValue = aDefaultValue;
    }

    /**
     * default constructor
     */
    public DoubleColumnInterpolationStrategy() {
        this.defaultValue = DEFAULT_DEFAULT_VALUE;
    }

    @Override
    public void handleWrongDataTypeInaColumn(String[][] matrix,
            int columnIndex, boolean[] flags) {
        for (int i = 0; i < matrix.length; i++) {
            if (!flags[i]) {
                int k = i - 1;
                while (k >= 0 && flags[k] == false) {
                    k--;
                }
                double lastValue = this.defaultValue;
                double nextValue = this.defaultValue;
                if (k >= 0) {
                    lastValue = Double.parseDouble(matrix[k][columnIndex]);
                }

                int j = i + 1;
                while (j < matrix.length && flags[j] == false) {
                    j++;
                }

                if (j < matrix.length) {
                    nextValue = Double.parseDouble(matrix[j][columnIndex]);
                }

                if (k >= 0 && j < matrix.length) {
                    matrix[i][columnIndex] = Double
                            .toString(((lastValue + nextValue) / 2));
                } else if (k >= 0) {
                    matrix[i][columnIndex] = Double.toString(lastValue);
                } else if (j < matrix.length) {
                    matrix[i][columnIndex] = Double.toString(nextValue);
                } else {
                    matrix[i][columnIndex] = Double.toString(this.defaultValue);
                }

            }
        }
        return;
    }

    @Override
    public String getName() {
        return "Interpolation";
    }

    @Override
    public String toString() {
        return this.getName();
    }
    
}

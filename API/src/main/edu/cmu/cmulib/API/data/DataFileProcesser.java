package edu.cmu.cmulib.API.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 * @author Cambi, Soumya
 *
 */
class DataFileProcesser {

    private DelimiterErrorStrategy notEnoughDelimiterStrategy;

    private DelimiterErrorStrategy tooManyDelimiterStrategy;

    private NotEnoughRowsStrategy notEnoughRowStrategy;

    // not handled yet
    private TooManyRowsStrategy tooManyRowsStrategy;

    private WrongDataTypeStrategy wrongDataTypeStrategy;

    /**
     * @param strategy
     *            the notEnoughDelimiterStrategy to set
     */
    public void setNotEnoughDelimiterStrategy(DelimiterErrorStrategy strategy) {
        this.notEnoughDelimiterStrategy = strategy;
    }

    /**
     * @param strategy
     *            the tooManyDelimiterErrorStrategy to set
     */
    public void setTooManyDelimiterErrorStrategy(DelimiterErrorStrategy strategy) {
        this.tooManyDelimiterStrategy = strategy;
    }

    /**
     * @param strategy
     *            the notEnoughRowStrategy to set
     */
    public void setNotEnoughRowStrategy(NotEnoughRowsStrategy strategy) {
        this.notEnoughRowStrategy = strategy;
    }

    /**
     * @param strategy
     *            the tooManyRowsErrorStrategy to set
     */
    public void setTooManyRowsErrorStrategy(TooManyRowsStrategy strategy) {
        this.tooManyRowsStrategy = strategy;
    }

    /**
     * @param strategy
     *            the wrongDataTypeStrategy to set
     */
    public void setWrongDataTypeStrategy(WrongDataTypeStrategy strategy) {
        this.wrongDataTypeStrategy = strategy;
    }

    /**
     * 
     */
    public DataFileProcesser() {
        this.setNotEnoughDelimiterStrategy(null);
        this.setTooManyDelimiterErrorStrategy(null);
        this.setNotEnoughRowStrategy(null);
        this.setTooManyRowsErrorStrategy(null);
        this.setWrongDataTypeStrategy(null);
    }

    /**
     * Passes data to SVD (assuming SVD's Master contains getData method)
     * 
     * @param numOfRows
     *            number of rows supposed to process in the file
     * @param numOfColumns
     *            number of rows supposed to process in the file
     * @param srcDataFile
     *            path of the source data
     * @param delimiter
     *            delimiter used to parse each line
     * @param dataType
     *            type of the data.
     * @return String matrix the processed data
     * @throws Exception
     *             when it happens
     */
    public String[][] processingData(int numOfRows, int numOfColumns,
            String srcDataFile, String delimiter, String dataType)
            throws Exception {

        String[][] matrix = new String[numOfRows][numOfColumns];

        // Reading data from file
        BufferedReader br = new BufferedReader(new FileReader(srcDataFile));
        String line = br.readLine();
        int numRowsSeen = 0;
        while (line != null && numRowsSeen < numOfRows) {
            String[] tokens = line.split(delimiter);

            // Check and correct data types
            boolean[] flags = this.typeCheck(tokens, dataType);
            if (!this.chcekSuccessful(flags) && wrongDataTypeStrategy != null) {
                tokens = this.wrongDataTypeStrategy.handleWrongDataTypeInaRow(
                        tokens, flags);
            }

            // Check and correct the number of tokens
            if (tokens.length > numOfColumns
                    && tooManyDelimiterStrategy != null) {
                tokens = this.tooManyDelimiterStrategy
                        .handleWrongNumElementInaRow(tokens, numOfColumns, line);
            }

            if (tokens.length < numOfColumns
                    && notEnoughDelimiterStrategy != null) {
                tokens = this.notEnoughDelimiterStrategy
                        .handleWrongNumElementInaRow(tokens, numOfColumns, line);
            }

            for (int i = 0; i < tokens.length; i++) {
                matrix[numRowsSeen][i] = tokens[i];
            }

            line = br.readLine();

            numRowsSeen++;
        }

        // check and correct the number of rows
        if (numRowsSeen < numOfRows && notEnoughRowStrategy != null) {
            matrix = this.notEnoughRowStrategy.handleLackOfRows(matrix,
                    numOfRows, numRowsSeen);
            numOfRows = matrix.length;
        } else if (line != null && tooManyRowsStrategy != null) {
            matrix = this.tooManyRowsStrategy.handleTooManyRows(matrix,
                    numOfRows, line, br);
            numOfRows = matrix.length;
        }

        br.close();
        return matrix;
    }

    /**
     * check the type in the given tokens
     * 
     * @param tokens
     *            the given tokens to check
     * @param dataType
     *            the designed data type
     * @return an array of boolean. Each element in the array was false if the
     *         element with the same index in tokens[] is not valid
     */
    private boolean[] typeCheck(String[] tokens, String dataType) {
        boolean[] flags = new boolean[tokens.length];
        Arrays.fill(flags, true);
        return flags;
    }

    /**
     * check if there is any false in a give boolean array
     * 
     * @param flags
     *            the boolean array to check
     * @return false if any of the element in flags is false
     */
    private boolean chcekSuccessful(boolean[] flags) {
        for (boolean b : flags) {
            if (!b)
                return false;
        }
        return true;
    }

}

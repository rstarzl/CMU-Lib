package edu.cmu.cmulib.API.data;

/**
 * a DataAPI used to read file and pass it to the Master Node Sample Usage:
 * 
 * Example:
 *       DataAPI processor = new DataAPI();
 *       
 *       IgnoreRearTokensStrategy moreStra = new IgnoreRearTokensStrategy();
 *       PaddingRearWithValueStrategy lessStra = new PaddingRearWithValueStrategy("9.9");
 *       processor.setNotEnoughDelimiterStrategy(lessStra);
 *       processor.setTooManyDelimiterErrorStrategy(moreStra);
 *       processor.processingData(1000, 100, fileName, ",", "dataType");
 * 
 * @author Cambi
 */
public class DataAPI {

    private final DataFileProcesser fileProcesser = new DataFileProcesser();

    /**
     * set the strategy when there are not enough delimiters in a row in the
     * input file
     * 
     * @param strategy
     *            a {@link DelimiterErrorStrategy}
     */
    public void setNotEnoughDelimiterStrategy(DelimiterErrorStrategy strategy) {
        this.fileProcesser.setNotEnoughDelimiterStrategy(strategy);
    }

    /**
     * set the strategy when there are too many delimiters in a row in the input
     * file
     * 
     * @param strategy
     *            a {@link DelimiterErrorStrategy}
     */
    public void setTooManyDelimiterStrategy(DelimiterErrorStrategy strategy) {
        this.fileProcesser.setTooManyDelimiterErrorStrategy(strategy);
    }

    /**
     * set the strategy when there are not enough rows in the input file
     * 
     * @param strategy
     *            a {@link DelimiterErrorStrategy}
     */
    public void setNotEnoughRowsStrategy(NotEnoughRowsStrategy strategy) {
        this.fileProcesser.setNotEnoughRowStrategy(strategy);
    }

    /**
     * set the strategy when there are too many rows in the input file
     * 
     * @param strategy
     *            a {@link DelimiterErrorStrategy}
     */
    public void setTooManyRowsStrategy(TooManyRowsStrategy strategy) {
        this.fileProcesser.setTooManyRowsErrorStrategy(strategy);
    }

    /**
     * set the strategy when there is a field with wrong data type in the input
     * file
     * 
     * @param strategy
     *            a {@link DelimiterErrorStrategy}
     */
    public void setWrongDataTypeStrategy(WrongDataTypeStrategy strategy) {
        this.fileProcesser.setWrongDataTypeStrategy(strategy);
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
     * @return true if the import is successful
     */
    public Boolean passingData(int numOfRows, int numOfColumns,
            String srcDataFile, String delimiter, String dataType) {

        try {
            String[][] tokens = this.fileProcesser.processingData(numOfRows,
                    numOfColumns, srcDataFile, delimiter, dataType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Sends data to Master's acceptData method
        return (Master.acceptData(numSlaves, tokens, numOfRows, numOfColumns,
                dataType));

    }
}

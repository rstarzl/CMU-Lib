package edu.cmu.cmulib.data.API;

/**
 * a strategy to handle error in number of delimiters
 */
public interface DelimiterErrorStrategy {

    /**
     * handles the wrong number of tokens in the given row
     * 
     * @param tokens
     *            a String array that stores the original tokens
     * @param numTokens
     *            number of tokens this row is supposed to contain
     * @return a String array containing the data with correct number of tokens
     * 
     * ensures: return.length == numTokens
     */
    String[] handleWrongNumElementInaRow(String[] tokens, int numTokens);

}

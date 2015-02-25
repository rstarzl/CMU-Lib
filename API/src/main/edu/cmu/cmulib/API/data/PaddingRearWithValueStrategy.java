/**
 * 
 */
package edu.cmu.cmulib.API.data;

import java.util.Arrays;

/**
 * Padding tokens in the rear when there are not enough tokens
 * 
 * @author Cambi
 */
public class PaddingRearWithValueStrategy implements DelimiterErrorStrategy {

    private final String paddingString;

    /**
     * default constructor
     * @param aPaddingString the value used for padding
     */
    public PaddingRearWithValueStrategy(String aPaddingString) {
        this.paddingString = aPaddingString;
    }

    /**
     * {@inheritDoc}
     * 
     * requires tokens.length <= numTokens and numTokens > 0
     * 
     * Padding tokens in the rear using paddingString when there are not enough
     * tokens
     */
    @Override
    public String[] handleWrongNumElementInaRow(String[] tokens, int numTokens, String rawData) {
        if (tokens.length > numTokens) {
            throw new IllegalStateException("More tokens than expected");
        }
        if (numTokens < 0) {
            throw new IllegalArgumentException("negative numTokens");
        }
        String[] result = Arrays.copyOf(tokens, numTokens);
        Arrays.fill(result, tokens.length, numTokens, this.paddingString);
        return result;
    }

}

/**
 * 
 */
package edu.cmu.cmulib.API.data;

import java.util.Arrays;

/**
 * Delete tokens in the rear when there are too many tokens
 * @author Cambi
 */
public class IgnoreRearTokensStrategy implements DelimiterErrorStrategy {

    /**
     * constructor
     */
    public IgnoreRearTokensStrategy() {
    }

    /** 
     * {@inheritDoc}
     * 
     * requires tokens.length >= numTokens and numTokens > 0
     * Delete tokens in the rear when there are too many tokens
     */
    @Override
    public String[] handleWrongNumElementInaRow(String[] tokens, int numTokens, String rawData) {
        if (tokens.length < numTokens) {
            throw new IllegalStateException("Less tokens than expected");
        }
        if (numTokens < 0) {
            throw new IllegalArgumentException("negative numTokens");
        }
        return Arrays.copyOf(tokens, numTokens);
    }

}

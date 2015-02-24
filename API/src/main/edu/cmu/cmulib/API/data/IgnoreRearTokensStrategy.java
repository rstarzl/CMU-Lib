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
     * requires tokens.length >= numTokens
     * Delete tokens in the rear when there are too many tokens
     */
    @Override
    public String[] handleWrongNumElementInaRow(String[] tokens, int numTokens) {
        if (tokens.length < numTokens) {
            throw new IllegalStateException("Less tokens than expected");
        }
        return Arrays.copyOf(tokens, numTokens);
    }

}

/**
 * 
 */
package edu.cmu.cmulib.API.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Cambi
 *
 */
public class IgnoreRearTokensStrategyTest {

    @Test
    public void test() {
        String[] tokens = new String[3];
        tokens[0] = "0.0";
        tokens[1] = "1.0";
        tokens[2] = "2.0";
        IgnoreRearTokensStrategy strategy = new IgnoreRearTokensStrategy();
        String[] pTokens = strategy.handleWrongNumElementInaRow(tokens, 0, "");
        assertEquals(pTokens.length, 0);
        pTokens = strategy.handleWrongNumElementInaRow(tokens, 1, "");
        assertEquals(pTokens.length, 1);
        assertEquals(pTokens[0], "0.0");
        
        pTokens = strategy.handleWrongNumElementInaRow(tokens, 2, "");
        assertEquals(pTokens.length, 2);
        assertEquals(pTokens[0], "0.0");
        assertEquals(pTokens[1], "1.0");

    }

}

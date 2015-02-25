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
public class PaddingRearWithValueStrategyTest {

    @Test
    public void test() {
        String[] tokens = new String[2];
        tokens[0] = "0.0";
        tokens[1] = "1.0";
        PaddingRearWithValueStrategy strategy = new PaddingRearWithValueStrategy("9.9");
        String[] pTokens = strategy.handleWrongNumElementInaRow(tokens, 3, "");
        assertEquals(pTokens.length, 3);
        assertEquals(pTokens[0], "0.0");
        assertEquals(pTokens[1], "1.0");
        assertEquals(pTokens[2], "9.9");
        
        pTokens = strategy.handleWrongNumElementInaRow(tokens, 4, "");
        assertEquals(pTokens.length, 4);
        assertEquals(pTokens[0], "0.0");
        assertEquals(pTokens[1], "1.0");
        assertEquals(pTokens[2], "9.9");
        assertEquals(pTokens[3], "9.9");
    }

}

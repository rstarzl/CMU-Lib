/**
 * 
 */
package edu.cmu.cmulib.API.data;

import static org.junit.Assert.*;
import jdk.nashorn.internal.objects.annotations.Setter;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Cambi
 *
 */
public class DoubleColumnInterpolationStrategyTest {

    private String[][] data = new String[3][2];
    private boolean[] flags = new boolean[3];
    private WrongDataTypeStrategy stra = new DoubleColumnInterpolationStrategy(
            9.0);

    @Before
    public void setUp() {
        data[0][0] = "1.0";
        data[1][0] = "2.0";
        data[2][0] = "3.0";
        data[0][1] = "1.5";
        data[1][1] = "2.5";
        data[2][1] = "3.5";

        flags[0] = true;
        flags[1] = true;
        flags[2] = true;
    }

    @Test
    public void testNormal() {
        data[1][0] = "";
        flags[1] = false;
        this.stra.handleWrongDataTypeInaColumn(data, 0, flags);
        assertEquals(data[0][0], "1.0");
        assertEquals(data[0][1], "1.5");
        assertEquals(data[1][0], "2.0");
        assertEquals(data[1][1], "2.5");
        assertEquals(data[2][0], "3.0");
        assertEquals(data[2][1], "3.5");
    }
    
    @Test
    public void testFirstOne() {
        data[0][0] = "";
        data[1][0] = "";
        flags[0] = false;
        flags[1] = false;
        this.stra.handleWrongDataTypeInaColumn(data, 0, flags);
        assertEquals(data[0][0], "3.0");
        assertEquals(data[0][1], "1.5");
        assertEquals(data[1][0], "3.0");
        assertEquals(data[1][1], "2.5");
        assertEquals(data[2][0], "3.0");
        assertEquals(data[2][1], "3.5");
    }
    
    @Test
    public void testAllEmpty() {
        data[0][0] = "";
        data[1][0] = "";
        data[2][0] = "";
        flags[0] = false;
        flags[1] = false;
        flags[2] = false;
        this.stra.handleWrongDataTypeInaColumn(data, 0, flags);
        assertEquals(data[0][0], "9.0");
        assertEquals(data[0][1], "1.5");
        assertEquals(data[1][0], "9.0");
        assertEquals(data[1][1], "2.5");
        assertEquals(data[2][0], "9.0");
        assertEquals(data[2][1], "3.5");
    }
    
    @Test
    public void testLastOne() {
        data[2][0] = "";
        flags[2] = false;
        this.stra.handleWrongDataTypeInaColumn(data, 0, flags);
        assertEquals(data[0][0], "1.0");
        assertEquals(data[0][1], "1.5");
        assertEquals(data[1][0], "2.0");
        assertEquals(data[1][1], "2.5");
        assertEquals(data[2][0], "2.0");
        assertEquals(data[2][1], "3.5");
    }

}

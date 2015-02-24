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
public class DataFileProcesserTest {

    @Test
    public void testNormalFile() throws Exception {
        String fn = "src/test/resources/data/testProcessorNormal";
        DataFileProcesser processor = new DataFileProcesser();
        String[][] matrix = processor.processingData(3, 2, fn, ",", "dataType");
        assertEquals(matrix.length, 3);
        assertEquals(matrix[0].length, 2);
        assertEquals(matrix[0][0], "1.0");
        assertEquals(matrix[0][1], "1.1");
        assertEquals(matrix[1][0], "2.0");
        assertEquals(matrix[1][1], "2.1");
        assertEquals(matrix[2][0], "3.0");
        assertEquals(matrix[2][1], "3.1");
    }
    
    @Test
    public void testFileDelimiters() throws Exception {
        String fn = "src/test/resources/data/testProcessorDelimiter";
        DataFileProcesser processor = new DataFileProcesser();
        IgnoreRearTokensStrategy moreStra = new IgnoreRearTokensStrategy();
        PaddingRearWithValueStrategy lessStra = new PaddingRearWithValueStrategy("9.9");
        processor.setNotEnoughDelimiterStrategy(lessStra);
        processor.setTooManyDelimiterErrorStrategy(moreStra);
        
        String[][] matrix = processor.processingData(3, 2, fn, ",", "dataType");
        assertEquals(matrix.length, 3);
        assertEquals(matrix[0].length, 2);
        assertEquals(matrix[0][0], "1.0");
        assertEquals(matrix[0][1], "1.1");
        assertEquals(matrix[1][0], "2.0");
        assertEquals(matrix[1][1], "9.9");
        assertEquals(matrix[2][0], "3.0");
        assertEquals(matrix[2][1], "3.1");
    }

}

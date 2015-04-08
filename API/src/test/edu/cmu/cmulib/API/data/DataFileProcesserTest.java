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
    
    @Test
    public void testWrongData() throws Exception {
        String fn = "src/test/resources/data/testWrongData";
        DataFileProcesser processor = new DataFileProcesser();
        processor.setWrongDataTypeStrategy(new DoubleColumnInterpolationStrategy(9.9));
        
        String[][] matrix = processor.processingData(3, 2, fn, ",", "dataType");
        assertEquals(matrix.length, 3);
        assertEquals(matrix[0].length, 2);
        assertEquals(matrix[0][0], "1.0");
        assertEquals(matrix[0][1], "1.1");
        assertEquals(matrix[1][0], "2.0");
        assertEquals(matrix[1][1], "2.1");
        assertEquals(matrix[2][0], "3.0");
        assertEquals(matrix[2][1], "3.1");
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}

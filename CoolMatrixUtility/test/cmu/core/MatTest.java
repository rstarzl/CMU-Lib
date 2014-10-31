package cmu.core;

import org.junit.Test;
import cmu.core.Mat;

public class MatTest {
	/*
	 * Transpoes Test
	 */
    @Test
    public void TestTransPose_empty_matrix() {
    	double[] data = {};
    	Mat mat = new Mat(0, 0, data);
    	Mat m = mat.t();
    	double[] expected = {};
    	 org.junit.Assert.assertArrayEquals("transpose failure on empty matrix", expected, m.data, 0.00001);
    }	
	
    @Test
    public void TestTransPose_1by1() {
    	double[] data = {1};
    	Mat mat = new Mat(1, 1, data);
    	Mat m = mat.t();
    	double[] expected = {1};
    	 org.junit.Assert.assertArrayEquals("transpose failure on 1*1 matrix", expected, m.data, 0.00001);
    }	
	
    @Test
    public void TestTransPose_2by2() {
    	double[] data = {1,2,3,4};
    	Mat mat = new Mat(2, 2, data);
    	Mat m = mat.t();
    	double[] expected = {1,3,2,4};
    	 org.junit.Assert.assertArrayEquals("transpose failure on 2*2 matrix", expected, m.data, 0.00001);
    }
    
    @Test
    public void TestTransPose_5by5() {
    	double[] data = new double[25];
    	for (int i = 0; i < 25; i++) {
    		data[i] = i + 1;
    	}
    	Mat mat = new Mat(5, 5, data);
    	Mat m = mat.t();
    	double[] expected = 
    		   {1, 6, 11, 16, 21,
    			2, 7, 12, 17, 22,
    			3, 8, 13, 18, 23,
    			4, 9, 14, 19, 24,
    			5, 10, 15, 20, 25};
    	 org.junit.Assert.assertArrayEquals("transpose failure on 2*2 matrix", expected, m.data, 0.00001);
    }
    
    @Test
    public void TestTransPose_2by3() {
    	double[] data = new double[6];
    	for (int i = 0; i < 6; i++) {
    		data[i] = i + 1;
    	}
    	Mat mat = new Mat(2, 3, data);
    	Mat m = mat.t();
    	double[] expected = 
    		   {1,3,5,2,4,6};
    	 org.junit.Assert.assertArrayEquals("transpose failure on 2*3 matrix", expected, m.data, 0.00001);
    }
    
	/*
	 * isEmpty Test
	 */
    @Test
    public void TestIsEmpty_Empty_matrix() {
    	double[] data = {};
    	Mat mat = new Mat(0, 0, data);
    	boolean state = mat.isEmpty();
    	boolean expected = true;
    	 org.junit.Assert.assertEquals("isEmpty() failure on empty matrix", expected, state);
    }	
	
    @Test
    public void TestIsEmpty_NoEmpty_matrix() {
    	double[] data = {1};
    	Mat mat = new Mat(1, 1, data);
    	boolean state = mat.isEmpty();
    	boolean expected = false;
    	 org.junit.Assert.assertEquals("isEmpty() failure on non-empty matrix", expected, state);
    }	
	
	/*
	 * at(x,y) Test
	 */
    @Test
    public void TestAt_test1() {
    	double[] data = {1,2,3,4};
    	Mat mat = new Mat(2, 2, data);
    	double m = mat.at(1,1);
    	double expected = 4;
    	 org.junit.Assert.assertEquals("at() failure on 2*2 matrix", expected, m, 0.0000001);
    }
    
    // mul
    @Test
    public void TestMul_2by2() {
    	double[] data = new double[4];
    	for (int i = 0; i < 4; i++) {
    		data[i] = i + 1;
    	}
    	Mat mat = new Mat(2, 2, data);
    	Mat m = mat.mul(2);
    	double[] expected = 
    		   {2, 4, 6, 8};
    	 org.junit.Assert.assertArrayEquals("mul failure on 2*2 matrix", expected, m.data, 0.00001);
    }
    
    @Test
    public void TestColRange() {
      	double[] data = {1,2,3,4,5,6,7,8,9,10,11,12};
    	Mat mat = new Mat(3, 4, data);
    	Mat m = mat.colRange(1,2);
    	double[] expected = 
    		   {4,5,6,7,8,9};
    	org.junit.Assert.assertArrayEquals("colRange failure", expected, m.data, 0.00001);
    }
}

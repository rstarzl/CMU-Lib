package cmu.core;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class MatMulTimeTest {
	private static int size = 3000;
	private static double[] data;
	private static Mat M1;
	private static Mat M2;
	@Parameterized.Parameters
    public static List<Object[]> data() {
		data = new double[size*size];
    	for (int i = 0 ; i < size*size; i++) {
    		data[i] = Math.random();
    	}
    	M1 = new Mat(size,size,data);
    	M2 = new Mat(size,size,data);
        return Arrays.asList(new Object[3][0]);
    }
    
	@Test
    public void TestMul() {
    	MatOp.gemm(M1, M2);
    }
}

package edu.cmu.cmulib.SVD;

import java.util.Random;

import edu.cmu.cmulib.API.data.*;

public class calculate1svd {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String input = "/Users/yingsheng/git/CMU-Lib/API/src/test/resources/data/testMat";
		String output = "/Users/yingsheng/testMatout";
		// TODO Auto-generated method stub
		svdMaster(input, output);
	}
	
	public static String svdMaster(String input, String output) throws Exception {
		//input = "/Users/yingsheng/git/CMU-Lib/API/src/test/resources/data/testMat";
		//output = "/Users/yingsheng/testMatout";
		
		output = output + "/svdResult";
		DataFileProcesser processor = new DataFileProcesser();
        String[][] stringMat = processor.processingData(3, 4, input, ",", "dataType");
        Matrix mat = new Matrix(stringMat);
        int rown = mat.row;

        Matrix L1 = Matrix.getRandMat(mat.row, 1);
        Matrix L = Matrix.getRandMat(mat.row, 1);
        L.normalize();
        
        int slave_num = 2;
        int slave_size = mat.col / slave_num;
        
        double thr = 1e-10;
        Matrix e_new = new Matrix(mat.row, mat.col, 100);
        Matrix e = new Matrix(mat.row, mat.col, 0); 
        while (Matrix.getDiff(e, e_new) != 0) {
        	System.out.println(Matrix.getDiff(e, e_new));
    
        	for (int i = 0; i < slave_num; i++) {
        		int start = i * slave_size;
        		Matrix submat = mat.getSubMat(start, slave_size);
        		Matrix tmp = submat.multiply(submat.transpose()).multiply(L);
        	    L1.add(tmp);
        	}
        	
        	L1.normalize();
        	L = L1;
        	System.out.println(L.firstColToStr());
        	e = e_new;
        	System.out.println("L row" + L.row + "  L col " + L.col + "mat row" + mat.row + " mat col " + mat.col);
        	Matrix tmp = L.multiply(mat.transpose().multiply(L).transpose());
        	e_new = mat.minus(tmp);
        	System.out.println("end" + Matrix.getDiff(e, e_new));
        }      
        L.writeToFile(output);
        return output;
        
	}
	
	

}

package edu.cmu.cmulib.SVD;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.cmulib.API.data.*;
import edu.cmu.cmulib.gui.UI;

public class Calculate1svd {
	
    
	private UI gui;
    private static final int NUM_COLUMNS = 100;
    private static final int NUM_ROWS = 100;

    private static final double THRESHOLD_SVD = 0;
    private static final int MAX_ITER = 100;
    
	
	DataFileProcesser processor = new DataFileProcesser();
	
    /**
     * @return the processor
     */
    public DataFileProcesser getProcessor() {
        return processor;
    }

    private final List<WrongDataTypeStrategy> wrongDataStrategies = new ArrayList<WrongDataTypeStrategy>();
	
	/**
     * @return the wrongDataStrategies
     */
    public List<WrongDataTypeStrategy> getWrongDataStrategies() {
        return wrongDataStrategies;
    }
    
    public void registerDataStrategy(WrongDataTypeStrategy stra) {
        this.wrongDataStrategies.add(stra);
    }

    public void setUI(UI inputUI){
		gui = inputUI;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String input = "/Users/yingsheng/git/CMU-Lib/API/src/test/resources/data/testMat";
		String output = "/Users/yingsheng/testMatout";
		// TODO Auto-generated method stub
		
		// svdMaster(input, output);
	}
	
	public String svdMaster(String input, String output) throws Exception {
		//input = "/Users/yingsheng/git/CMU-Lib/API/src/test/resources/data/testMat";
		//output = "/Users/yingsheng/testMatout";
		gui.updateprogressArea("***************** Start Job ***************************\n");
		gui.updateprogressArea("Loading Data \n ... \n");
		
		output = output + "/svdResult";
		
        String[][] stringMat = processor.processingData(NUM_ROWS, NUM_COLUMNS, input, ",", "dataType");
        Matrix mat = new Matrix(stringMat);

        Matrix L1 = Matrix.getRandMat(mat.row, 1);
        Matrix L = Matrix.getRandMat(mat.row, 1);
        L.normalize();
        
        int slave_num = 2;
        int slave_size = mat.col / slave_num;
        
        Matrix e_new = new Matrix(mat.row, mat.col, 100);
        Matrix e = new Matrix(mat.row, mat.col, 0); 
        gui.updateprogressArea("Start Computing \n");
        
        int count = 0;
        while (Matrix.getDiff(e, e_new) > THRESHOLD_SVD) {
    
        	for (int i = 0; i < slave_num; i++) {
        		int start = i * slave_size;
        		Matrix submat = mat.getSubMat(start, slave_size);
        		Matrix tmp = submat.multiply(submat.transpose()).multiply(L);
        	    L1.add(tmp);
        	}
        	
        	L1.normalize();
        	L = L1;
        	System.out.println(L.firstColToStr());
        	gui.updateprogressArea(L.firstColToStr());
        	e = e_new;
        	System.out.println("L row" + L.row + "  L col " + L.col + "mat row" + mat.row + " mat col " + mat.col);
        	Matrix tmp = L.multiply(mat.transpose().multiply(L).transpose());
        	e_new = mat.minus(tmp);
        	System.out.println("diff(e, e_new): " + Matrix.getDiff(e, e_new));
        	if (++count >= 100) {
        		break;
        	}
        }    
        L.writeToFile(output);
        gui.updateprogressArea("***************** Finish Job *******************\n");
        return output;     
	}
	
	

}

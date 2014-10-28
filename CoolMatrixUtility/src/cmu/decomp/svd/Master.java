package cmu.decomp.svd;

import java.util.ArrayList;

import cmu.core.Mat;
import cmu.core.MatOp;
import cmu.help.Tag;

public class Master {
	
	public static void main (String[] args) {
		double[] test = {1,2,3,4,5,6,7,8,9,10,11,12};
		int rows = 3;
		int cols = 4;
		int slaveNum = 1;
		Mat score = new Mat(rows, cols ,test);
		Tag tag;
		Mat Like, slaveL;
		Master_MidWare commu = new Master_MidWare();
		
		Master_Spliter split = new Master_Spliter(score, slaveNum);
		Master_SVD svd = new Master_SVD(score, slaveNum);
		
		Like = svd.initL();
		slaveL = null;
		do {
			svd.setL(Like);
			commu.push(Like);
			ArrayList<Tag> index = split.split();
			for(int i = 0; i < index.size(); i++) {
				tag = index.get(i);
				commu.push(tag);
			}	
			for (int i = 0; i < slaveNum; i++) {
//				do {
					slaveL = commu.pull();  
//				} while (slaveL == null);
				svd.update_SVD(slaveL);
			}
			Like = svd.getUpdateL();
			MatOp.vectorNormalize(Like, MatOp.NormType.NORM_L2);
//			System.out.println(Like.data[0] + "  " + Like.data[1]+ "  " + Like.data[2]);
		} while (!svd.isPerformed(Like));		
		System.out.println("final  " + Like.data[0] + "  " + Like.data[1]+ "  " + Like.data[2]);
	}
}

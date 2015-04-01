package edu.cmu.cmulib.DummyMaster;

import java.util.ArrayList;
import java.util.LinkedList;

import cmu.core.Mat;
import cmu.core.MatOp;
import cmu.decomp.svd.Master_SVD;
import cmu.decomp.svd.Master_Spliter;
import cmu.help.Tag;
import java.io.*;
import edu.cmu.cmulib.communication.CommonPacket;


public class Master {
	
	
		public static boolean acceptData(int numSlaves, String[][] tokens, int numOfRows, 
				int numOfColumns, String dataType) {
			return true;
		}
		
		
		public static void main (String[] args) throws IOException {
	        // 4 slaves assumed
	        double[] test = new double[1000*1000];
	        int q = 0;
			int slaveNum = 1;
			LinkedList<Double[]> mList = new LinkedList<Double[]>();
	
			String inPath = args[0];
			String outPath = args[1];
			BufferedReader br = new BufferedReader(new FileReader(inPath));
	        //BufferedReader br = new BufferedReader(new FileReader("svd.data.txt"));
	        String line;
	        while ((line = br.readLine()) != null) {
	            test[q] = Double.parseDouble(line);
	            q++;
	        }
	        br.close();
	        
	
	        // initialize original matrix
	        int rows = 1000;
	        int cols = 1000;
	        Mat score = new Mat(rows, cols ,test);
	        Tag tag;
	        Mat Like, slaveL;
	
	        int port = Integer.parseInt(args[0]);
	            
	        MasterMiddleWare commu = new MasterMiddleWare(port);
	        commu.register(Double[].class,mList);
	        commu.startMaster();
	            
	            
	        Master_Spliter split = new Master_Spliter(score, slaveNum);
	        Master_SVD svd = new Master_SVD(score, slaveNum);
	        while(commu.slaveNum()<slaveNum){System.out.println(commu.slaveNum());}
	        Like = svd.initL();
	        slaveL = null;
	         
	        // compute the first eigenvector iterately
	        do {
	            int remain = slaveNum;
	            svd.setL(Like);
	            printArray(Like.data);
	            // send L
	            for (int i = 1; i <= slaveNum; i++){
	                sendMat(Like,i,commu);
	             }
	            //send Tag
	                ArrayList<Tag> index = split.split();
	                for(int i = 0; i < index.size(); i++) {
	                    tag = index.get(i);
	                    CommonPacket packet = new CommonPacket(-1,tag);
	                    commu.sendPacket(i+1, packet);
	                }
	            // receive L and update
	              while (remain > 0) {
	                synchronized (mList) {
	                    if (mList.size() > 0) {
	                        slaveL = getMat(mList);
	                        svd.update_SVD(slaveL);
	                        remain--;
	                    }
	                }
	              }
	                    
	            Like = svd.getUpdateL();
	            MatOp.vectorNormalize(Like, MatOp.NormType.NORM_L2);
	        } while (!svd.isPerformed(Like));     //termination of iteration
	        System.out.println("final  ");
	        printArray(Like.data);
	        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(outPath)));
	        for (int i = 0; i < Like.data.length; i ++) {
	        	bw.write(Like.data[i] + " ");
	        }
	        bw.close();
        }
		public static void printArray(double[] arr){
			for(double i: arr)
				System.out.print(i+" ");
			System.out.println();
		}
		
		public static Mat getMat(LinkedList<Double[]> mList){
			Double [] temp = mList.peek();
	    	double row = temp[0];
	    	double col = temp[1];
	    	double [] arr = new double[temp.length-2];
	    	for(int k=0;k<arr.length;k++){
	    		arr[k] = temp[k+2];
	    	}
	    	Mat mat = new Mat((int)row,(int)col,arr);    	
	        mList.remove();
	        return mat;
			
		}
		
		
		public static void sendMat(Mat mat,int id, MasterMiddleWare m){
			Double [] array = new Double[mat.data.length+2];
		    array[0] = Double.valueOf(mat.rows);
		    array[1] = Double.valueOf(mat.cols);
		    
		    for(int k=0; k<mat.data.length;k++)
		    	array[k+2] = Double.valueOf(mat.data[k]);
	        CommonPacket packet = new CommonPacket(-1, array);
	        
	        m.sendPacket(id, packet);
			
		}
		
		/* To be implemented 
		   created by - Soumya Batra 
		   Objective - get data in a stream(1D Array)*/
		public static boolean getData(int numOfSlaves, String[] toks,
				int numOfRows, int numOfColumns, Boolean sentByRow,
				String dataType) {
			// TODO Auto-generated method stub
			return false;
		}
	
}

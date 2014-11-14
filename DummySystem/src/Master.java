import java.util.ArrayList;
import java.util.LinkedList;
import cmu.core.Mat;
import cmu.core.MatOp;
import cmu.decomp.svd.Master_SVD;
import cmu.decomp.svd.Master_Spliter;
import cmu.help.Tag;
import java.io.IOException;

import edu.cmu.cmulib.communication.CommonPacket;


public class Master {

		public static void main (String[] args) throws IOException {
        // 4 slaves assumed
		int slaveNum = 4;
		LinkedList<Double[]> mList = new LinkedList<Double[]>();

        // initialize original matrix
        double[] test = {6,8,9,6,2,9,7,7,8,5,8,7,4,8,6,8,5,4,7,3,5,9,8,6,9,6,7,8,6,6,6,8};
        int rows = 8;
        int cols = 4;
        Mat score = new Mat(rows, cols ,test);
        Tag tag;
        Mat Like, slaveL;
            
        MasterMiddleWare commu = new MasterMiddleWare();
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
        
        /*
        System.out.println("PPPPPPPPPPPPPPPP");
        double [] a = {1.1, 2.2, 3.3, 4.4};
        int count =0;
        while (count<10){
        	count++;
        	int remain = 4;
            while (commu.slaveNum() != slaveNum){System.out.println(commu.slaveNum());}
            for (int i = 1; i <= slaveNum; i++){
            	Mat mat = new Mat(2,2,a);
        	    sendMat(mat,i,commu);
                
            }
            
            while (remain > 0) {
            	
                synchronized (mList) {
                    if (mList.size() > 0) {                
                    	Mat mat = getMat(mList);
                    	a = mat.data;
                    	remain--;
                    	
                    }
               }
            }
            System.out.println(a[0]+" "+a[1]+" "+a[2]+" "+a[3]);
        }
        */
        /*
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
		*/
        /*Double[] a = {1.1, 2.2, 3.3, 4.4};

        while (a[0] + a[1] + a[2] + a[3] < 100.0) {
            int remain = 4;
            while (commu.slaveNum() != slaveNum) {System.out.print(commu.slaveNum());}
            System.out.println("\n");

            for (int i = 1; i <= slaveNum; i++) {
            	CommonPacket packet = new CommonPacket(-1,a[i - 1]);
            	System.out.println("before send packet");
                commu.sendPacket(i, packet);
                System.out.println("after send packet");
            }

            while (remain > 0) {
                synchronized (commu.msgs) {
                    if (commu.msgs.size() > 0) {
                        System.out.println(commu.msgs.peek().para);
                        a[commu.msgs.peek().fromId - 1] = commu.msgs.peek().para;
                        commu.msgs.remove();
                        remain--;
                    }
                }
            }

            double sum = a[0] + a[1] + a[2] + a[3];*/
            //System.out.println("sum :" + sum);
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
		
		
		public static void sendMat(Mat mat,int id,MasterMiddleWare m){
			Double [] array = new Double[mat.data.length+2];
		    array[0] = Double.valueOf(mat.rows);
		    array[1] = Double.valueOf(mat.cols);
		    
		    for(int k=0; k<mat.data.length;k++)
		    	array[k+2] = Double.valueOf(mat.data[k]);
	        CommonPacket packet = new CommonPacket(-1, array);
	        
	        m.sendPacket(id, packet);
			
		}
	
}

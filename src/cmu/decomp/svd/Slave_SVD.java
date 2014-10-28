package cmu.decomp.svd;

import cmu.core.Mat;
import cmu.core.MatOp;

public class Slave_SVD {
    private Mat L;//src;          // original L and source Matrix

    public Slave_SVD(Mat src){
        // 	this.src = src;
        this.L = null;
    }
    public Slave_SVD(){

    }
    public Mat Slave_UpdateL(Mat src) {
        // Mat srcT = new Mat(src.rows, src.cols, src.data).t();

	/*	 Mat t = src.t();
		 for (int i = 0 ; i < t.data.length; i++){

			 if (i%t.cols == 0){
				 System.out.println();
			 }
			 System.out.print(t.data[i] + " ");
		 }*/
		/* System.out.println("aftertranspose");
		 for (int i =0 ; i < srcT.data.length; i++){

			 if (i%4 == 0){
				 System.out.println();
			 }
			 System.out.print(srcT.data[i] + " ");
		 }

		   return this.L;*/
		 /*
		 System.out.println("L: " + L.data[0] + "  " + L.data[1]);
		 for (int i = 0 ; i < src.data.length; i++){

			 if (i%src.cols == 0){
				 System.out.println();
			 }
			 System.out.print(src.data[i] + " ");
		 }
		 System.out.println("up is src");
		 Mat t = MatOp.gemm(src.t(), this.L);
		 for (int i = 0 ; i < t.data.length; i++){

			 if (i%t.cols == 0){
				 System.out.println();
			 }
			 System.out.print(t.data[i] + " ");
		 }
		 System.out.println();
		 */
        // Mat newL =  new Mat(MatOp.gemm(src, src.stransLmul(src.clone(), this.L)));
//		System.out.println("L before SVD");
//		this.L.display();
//		Mat temp = MatOp.gemm(src.t(), this.L);
//		System.out.println("S traspose mul L");
//		temp.display();
        this.L = MatOp.gemm(src, MatOp.gemm(src.t(), this.L));
//		  System.out.println("L: " + L.data[0] + "  " + L.data[1]);
        return this.L;
    }

    public void setL(Mat L){
        this.L = L;
    }
    public void setS(Mat S){
        // this.src = S;
    }
}
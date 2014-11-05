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
    /**
     * Slave_UpdateL
     *
     * update L by using the formula L=SS(transpose)L
     */
	 public Mat Slave_UpdateL(Mat src) {
		 this.L = MatOp.gemm(src, MatOp.gemm(src.t(), this.L));
		 return this.L;
	 }
	 
    /**
     * setL
     *
     * set L after receiving from master
     */
	 public void setL(Mat L){
		 this.L = L;
	 }
    /**
     * setS
     *
     * set matrix after reconstructing based on tag
     */
	 public void setS(Mat S){
		// this.src = S;
	 }
}

package cmu.decomp.svd;

import cmu.core.Mat;
import cmu.core.MatOp;

public class Slave_SVD {
	 private Mat _L, _S;
	 private Mat _subS;
	 public  Mat Slave_UpdateL(Mat L){
		   _L = MatOp.gemm(_S, MatOp.gemm(_subS.t(), L));
		   return _L;
	   }
	 public Slave_SVD(Mat S){
	 	_S = S;
	 }
	 public Mat getL(){
		 return _L;
	 }
}

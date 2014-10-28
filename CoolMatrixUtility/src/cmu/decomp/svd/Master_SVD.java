package cmu.decomp.svd;

import cmu.core.Mat;
import cmu.core.MatOp;
/**
 * 
 * @author mac
 *
 */
public class Master_SVD {
   private Mat L, updateL, src;          // original L, updated L, and source Matrix
   public int subNum;    				 // number of parts to split
   private static double EPS = 1e-7;     // The termination of iteration
   private static int MAX_ITER = 500;    // The max loop of iteration
   public int iter;						 // current numbers of loop for iteration
   private static double ALPHA = 0.90;   // coenfficient of iteration

	/**
	 * Master_SVD Constructor Initialize parameters.
	 * 
	 */
  public Master_SVD (Mat matrix, int subNum) {
	  this.src = matrix;
	  this.subNum = subNum;
	  this.L = new Mat(src.rows,1);
	  this.updateL = new Mat(src.rows,1);
	  this.iter = 0;
  }
  
  /**
   * initL
   * initialize L for start.
   */
  public Mat initL() {
	   Mat initL = new Mat(src.rows, 1);
	   for (int i = 0; i < src.rows; i++){
		   initL.data[i] = Math.random(); 
//		   initL.data[i] = i; 
	   }
	  // MatOp.norm(initL, MatOp.NormType.NORM_L2);
	   MatOp.vectorNormalize(initL, MatOp.NormType.NORM_L2);
//		System.out.println("init   " + initL.data[0] + "  " + initL.data[1]+ "  " + initL.data[2]);
	   return initL;
  }
  
  public void setL(Mat Like) {
	  this.L = Like;
//		System.out.println("setL" + this.L.data[0] + "  " + this.L.data[1]+ "  " + this.L.data[2]);
//		System.out.println("beforeSetL" + this.updateL.data[0] + "  " + this.updateL.data[1]+ "  " + this.updateL.data[2]);
	  this.updateL = Like.clone().mul(ALPHA);
//		System.out.println("setUpdateL" + this.updateL.data[0] + "  " + this.updateL.data[1]+ "  " + this.updateL.data[2]);
//		System.out.println("afterSetL" + this.L.data[0] + "  " + this.L.data[1]+ "  " + this.L.data[2]);
	  this.iter++;
  }
  
  public Mat getUpdateL () {
	  return this.updateL;
  }

  /**
   * isPerformed
   * compare original L with updated newL, to check if complete the iteration
   */
  public boolean isPerformed (Mat newL) {
//		System.out.println("thisL" + this.L.data[0] + "  " + this.L.data[1]+ "  " + this.L.data[2]);
//		System.out.println("newL" + newL.data[0] + "  " + newL.data[1]+ "  " + newL.data[2]);
	   if (MatOp.dist(newL, this.L, MatOp.NormType.NORM_L2) < EPS || iter > MAX_ITER) 
		   return true;
	   else 
		   return false;
  }
  
  /**
   * update_SVD
   * update L when new slaveL come
   */
  public void update_SVD (Mat slaveL) {
	  slaveL = slaveL.mul((1-ALPHA)/subNum);
	  this.updateL = MatOp.add(this.updateL, slaveL); 
  }
  
  
  /**
   * initialize
   * @param S  the rating matrix, column denotes movies, rows denotes users
   * @param N  the number of blocks that we want to split
   */

  /*
  public SVD(Mat S, int N){
	   this._N = N;
	   this._S = S;
	   _ss = new Slave_SVD [N];
	   _sp = new Spliter();
	   _alpha = new double[N];
	   for (int i = 0; i < _N; i++){
		   _ss[i] = new Slave_SVD();
		   _alpha[i] = 1.0 / _N;
	   }
	 
  }
  */
  /*
  public Mat perform_SVD(){
	    Mat new_S;
	    init_L();
	   for (int i = 0; i < _MAX_ITER; i++){
		   for (int j = 0; j < _N; j++){
			   _ss[j] = _sp.Split(_S, );  
		   }
		   for (int j = 0; j < _N; j++){
			   _ss[j].Slave_UpdateL(_L);
		   }
		   for (int j = 0; j < _N; j++){
			   _L = MatOp.scaleAdd(_ss[j].getL(), _alpha[j], _L);
		   }
		   for(int j = 0; j < _N; j++ ){
			   new_S = ag.aggragate();
		   }
		   if (MatOp.dist(new_S, _S, MatOp.NormType.NORM_L2) < _eps){
			   break;
		   }
	   }
	   return _L;
  }
  */
}

package cmu.decomp.svd;

import cmu.core.Mat;
import cmu.core.MatOp;
/**
 * 
 * @author mac
 *
 */
public class Master_SVD {
   private Mat _L, _S;
   private static double _eps = 1e-7;
   private static int _MAX_ITER = 500;
   private static double _alpha[];
   private Aggregator _ag;
   private Spliter _sp;
   private int _N;
   private Slave_SVD _ss[];
   /**
    * initialize
    * @param S  the rating matrix, column denotes movies, rows denotes users
    * @param N  the number of blocks that we want to split
    */

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
  private void init_L(){
	    _L = new Mat(_S.rows, 1);
	   for (int i = 0; i < _S.rows; i++){
		   _L.data[i] = Math.random(); 
	   }
	   MatOp.norm(_L, MatOp.NormType.NORM_L2);
  }
  
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
   
}

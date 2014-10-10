package cmu.decomp.svd;

import cmu.core.Mat;

/**
 * Slave_Spliter is used to reconstruct the sub matrix on slaves.. 
 * @param src
 * 		     Mat type, array to reconstruct.
 * @param currBegin
 * 		     begin index of original matrix.
 * @param currBegin
 * 		     end index of original matrix.
 */
public class Slave_Spliter implements Spliter {
	public Mat src;    					 // original matrix for SVD
	public Mat m;						 // new constructed matrix for SVD on slave
	public Master_Spliter master;		 // master
	public int currBegin;  				 // index of begin collums to reconstruct
	public int currEnd;    				 // index of end collums to reconstruct
	
	/**
	 * Master_Splitter Constructor Initialize parameters.
	 * 
	 * @param matrix
	 *            the original matrix to decompose.
	 * @param slave
	 *            slave number.
	 */
	public Slave_Spliter (Mat matrix, Master_Spliter master) {
		this.src = matrix;
		this.m = null;
		this.master = master;
		this.currBegin = 0;
		this.currEnd = 0;
	}
	/**
	 * Master_Splitter Constructor Initialize parameters.
	 * 
	 * @param matrix
	 *            the original matrix to decompose.
	 * @param slave
	 *            slave number.
	 * @param begin
	 * 			  begin index to reconstruct  
	 * @param end
	 * 			  end index to reconstruct
	 */
	public Slave_Spliter (Mat matrix, Master_Spliter master, int begin, int end) {
		this.src = matrix;
		this.m = null;
		this.master = master;
		this.currBegin = begin;
		this.currEnd = end;
	}
	
	/**
	 * check if the slave is ready to accept
	 * 
	 */
	public boolean isReady () {
		return true;
	}
	
	/**
	 * set currBegin and currEnd, check if the slave accept successfully
	 * 
	 * @param master
	 *            object of Master_Spliter, receive the index from master
	 */
	public boolean receive () {
		this.currBegin = this.master.currBegin;
		this.currEnd = this.master.currEnd;
		return false;
	}
	/**
	 * reconstruct a submatrix of matrix on master
	 * 
	 */
	public boolean construct(){
		if (!receive()) {
			return false;
		}
		int cols = this.currEnd - this.currBegin + 1;
		int rows = this.src.rows;
		Mat temp = new Mat(rows, cols);
		temp.create();
		for(int j = 0; j < cols * rows; j++){
			int r = j / cols;
			int c = j % cols;
			temp.data[j] = this.src.data[this.currBegin + r * rows + c];
		}
		this.m = temp;
		return true;
	}
	
}

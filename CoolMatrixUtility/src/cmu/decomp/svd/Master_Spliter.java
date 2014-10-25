package cmu.decomp.svd;

import java.util.ArrayList;

import cmu.core.*;

/**
 * Master_Spliter is used to Split the whole Score Array into pieces. 
 * And send the index to different slaves.
 * @param src
 * 		     Mat type, array to split.
 * @param NumberOfSlaves
 * 		     Number of Slaves in distributed system.
 */
public class Master_Spliter implements Spliter {
	
	public Mat src;    					 // matrix for SVD
	public ArrayList<Slave> slaves; 	 // use arraylist to store Slave objects
	public int subNum;    				 // number of parts to split
	public int currBegin;  				 // index of begin collums ready to send
	public int currEnd;    				 // index of end collums ready to send
	
	/**
	 * Master_Splitter Constructor Initialize parameters.
	 * 
	 * @param matrix
	 *            the original matrix to decompose.
	 * @param slaves
	 *            set of different slaves
	 * @param subNum
	 *            number of parts to split
	 */
	public Master_Spliter (Mat matrix,ArrayList<Slave> slaves, int subNum) {
		this.src = matrix;
		this.slaves = slaves;
		this.subNum = subNum;
		this.currBegin = 0;
		this.currEnd = -1;
	}
	
	/**
	 * Master_Splitter Constructor Initialize parameters.
	 * 
	 * @param matrix
	 *            the original matrix to decompose.
	 * @param slaves
	 *            set of different slaves
	 * @param subNum
	 *            number of parts to split
	 * @param begin
	 * 			  begin index to split  
	 * @param end
	 * 			  end index to split         
	 */
	public Master_Spliter (Mat matrix,ArrayList<Slave> slaves, int subNum, int begin, int end) {
		this.src = matrix;
		this.slaves = slaves;
		this.subNum = subNum;
		this.currBegin = begin;
		this.currEnd = end;
	}
	
	/**
	 * check if one slave is ready to accept
	 * 
	 * @param slave
	 *            object of Slave
	 */
	public boolean isReady (Slave slave) {
		return true;
	}
	
	/**
	 * send currBegin and currEnd to slave, check if one slave accept successfully
	 * 
	 * @param slave
	 *            object of Slave
	 */
	public boolean send (Slave slave) {
		if (split()) {
			return true;	
		} else {
			return false;
		}
	}
	/**
	 * set the begin and end index of a subpart of matrix for split, auto
	 * 
	 */
	public boolean split () {
		int numOfCols = (int) Math.ceil ( (double) this.src.cols / this.subNum);
		int splitCols = numOfCols;
		if (this.currBegin + numOfCols > this.src.cols) {
			splitCols = this.src.cols - numOfCols;
		}
		this.currBegin = this.currEnd + 1;
		this.currEnd = this.currBegin + splitCols -1;
		return true;
	}
	
	/**
	 * set the begin and end index of a subpart of matrix for split, manul
	 * 
	 */
	public boolean split (int begin, int end) {
		if (begin >= 0 && end < this.src.cols) {
			this.currBegin = begin;
			this.currEnd = end;
			return true;
		} else {
			return false;
		}
	}
	
}

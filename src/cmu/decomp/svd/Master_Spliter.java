package cmu.decomp.svd;


import java.util.ArrayList;

import cmu.core.*;
import cmu.help.Tag;

/**
 * Master_Spliter is used to Split the whole Score Array into pieces. 
 * And send the index to different slaves.
 * @param src
 * 		     Mat type, array to split.
 * @param subNum
 * 		     Number of Slaves in distributed system.
 */
public class Master_Spliter {
	
	public Mat src;    					 // matrix for SVD
	public int subNum;    				 // number of parts to split
	public int beginToSlave;		     // begin index of splited data to slave
	public int endToSlave;               // end index of splited data to slave
	private Tag tag;                     // the begin and end indexes of splited data are pachaged
	
	/**
	 * Master_Splitter Constructor Initialize parameters.
	 * 
	 */
	public Master_Spliter () {
		this.src = null;
		this.subNum = 0;
		this.beginToSlave = 0;
		this.endToSlave = 0;
	}
	/**
	 * Master_Splitter Constructor Initialize parameters.
	 * 
	 * @param matrix
	 *            the original matrix to decompose.
	 * @param subNum
	 *            number of parts to split      
	 */
	public Master_Spliter (Mat matrix, int subNum) {
		this.src = matrix;
		this.subNum = subNum;
	}
	
	public void setMatrix (Mat matrix) {
		this.src = matrix;
	}
	
	public void setSubNum (int num) {
		this.subNum = num;
	}
	
	
	/**
	 * origin data from matrix is splited equally to every slave. Indexes are stored in ArrayList typed of Tag.
	 * 
	 */
	public ArrayList<Tag> split () {
		int sizeOfSubPart = (int) Math.ceil ( (double) src.cols / subNum);
		ArrayList<Tag> index = new ArrayList<Tag>();
		for (int i = 0; i < subNum; i++) {
			beginToSlave = i * sizeOfSubPart;
			endToSlave = beginToSlave + sizeOfSubPart - 1;
			tag = new Tag(beginToSlave, endToSlave);
			index.add(tag);
		}

		return index;
	}
	
}

package cmu.decomp.svd;

import cmu.core.Mat;
import cmu.help.Tag;


public class Slave_getSplitedMatrix {
    public Mat src;    					 // original matrix for SVD
    public Tag tag;

    /**
     * Master_Splitter Constructor Initialize parameters.
     *
     * @param matrix
     *            the original matrix to decompose.
     */
    public Slave_getSplitedMatrix (Mat matrix) {
        this.src = matrix;
    }

    public void setTag (Tag tag) {
        this.tag = tag;
    }

    /**
     * reconstruct a submatrix of matrix on master
     *
     */
    public Mat construct(){
        int begin = this.tag.getBegin();
        int end = this.tag.getEnd();
//		System.out.println(begin +"   " + end);
		/*
		int cols = end - begin + 1;
		int rows = this.src.rows;
		Mat temp = new Mat(rows, cols);
		temp.create();
		for(int j = 0; j < cols * rows; j++){
			int r = j / cols;
			int c = j % cols;
			temp.data[j] = this.src.data[begin + r * rows + c];
		}
		return temp;
		*/
        Mat temp = this.src.colRange(begin, end);

		/*
		 * test
		 * 		System.out.println("after splitted");
		 * System.out.println("splited rows:  " + temp.rows + "splited cols:  " + temp.cols);
		 * temp.display();
		 *
		 */
        return temp;
    }

}
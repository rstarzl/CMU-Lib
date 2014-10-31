package cmu.core;

public class MatOp {
	/**
	 * Generalized matrix multiplication A*B
	 * 
	 * @param src1
	 *            matrix in left side of multiplication, i.e. A in A*B.
	 * @param src2
	 *            matrix in right side of multiplication, i.e. B in A*B.
	 * @return multiplication result of A*B.
	 */
	public static Mat gemm(Mat src1, Mat src2) {
		/*
		 * Naive implementation. No optimization.
		 */
		/*
		assert (src1.cols == src2.rows);
		Mat tSrc2 = src2.t(); // transpose matrix B, this will improve Cache
								// performance
		Mat dst = new Mat(src1.rows, src2.cols);
		dst.create();
		int rowIdx, colIdx, startIdxSrc1, startIdxSrc2;
		double sum = 0, j = 0;
		for (int i = 0; i < dst.data.length; i++) {
			rowIdx = i / src1.cols;
			colIdx = i % src1.cols;
			startIdxSrc1 = rowIdx * src1.cols;
			startIdxSrc2 = colIdx * src1.cols;
			sum = 0;
			for (j = 0; j < src1.cols; j++) {
				sum += src1.data[startIdxSrc1++] * tSrc2.data[startIdxSrc2++];
			}
			dst.data[i] = sum;
		}
		*/
		Mat dst = new Mat(src1.rows, src2.cols);
		src1.inner.mult(src2.inner, dst.inner);
		dst.data = dst.inner.getData();
//		System.out.println(dst.inner.toString());
		
		/*System.out.println("*****");
		 for (int i = 0 ; i < dst.data.length; i++){
				
			 if (i%dst.cols == 0){
				 System.out.println();
			 }
			 System.out.print(dst.data[i] + " ");
		 }
		 System.out.println();*/
		/*double s1[] = {1,1,1,1};
		double s2[] = {0,0};
		Mat dst = new Mat(2, 1);
			(new Mat(2,2,s1)).inner.mult((new Mat(2,1,s1)).inner,dst.inner);
			dst.data = dst.inner.getData();
			 for (int i =0 ; i < dst.inner.getData().length; i++){
				 
				 if (i%2 == 0){
					 System.out.println();
				 }
				 System.out.print(dst.inner.getData()[i] + " ");
			 }*/
		return dst;
	}

	/**
	 * Per-element sum of two matrix
	 * 
	 * @param src1
	 *            Mat type matrix
	 * @param src2
	 *            Mat type matrix
	 * @return
	 */
	public static Mat add(Mat src1, Mat src2) {
		if (src1.isEmpty()) {
			return src2;
		}
		if (src2.isEmpty()) {
			return src1;
		}
		assert (src1.rows == src2.rows && src1.cols == src2.cols);
		for (int i = 0; i < src1.data.length; i++) {
			src1.data[i] = src1.data[i] + src2.data[i];
		}
		return src1;
	}
	
	/**
	 * Calculates the sum of a scaled array and another array.
	 * 
	 * @param src1
	 *            Mat type matrix
	 * @param alpha
	 *            scale factor for first matrix
	 * @param src2
	 *            Mat type matrix
	 * @return
	 */
	public static Mat scaleAdd(Mat src1, double alpha, Mat src2) {
		if (src1.isEmpty()) {
			return src2;
		}
		if (src2.isEmpty()) {
			return src1.mul(alpha);
		}
		assert (src1.rows == src2.rows && src1.cols == src2.cols);
		for (int i = 0; i < src1.data.length; i++) {
			src1.data[i] = src1.data[i] * alpha + src2.data[i];
		}
		return src1;
	}

	/**
	 * Enumerate supported norm operation type
	 */
	public static enum NormType {
		NORM_L1, NORM_L2
	};

	/**
	 * Calculates a matrix norm, norm type is specified by NormType
	 * 
	 * @param src
	 *            Matrix that wants to perform norm operation.
	 * @param normType
	 *            Specify the norm type, which can be NormType.NORM_L1 or
	 *            NormType.NORM_L2 :
	 * @return
	 */
	public static double norm(Mat src, NormType normType) {
		if (src.isEmpty()) {
			return 0.0;
		} else {
			double sum = 0.0;
			switch (normType) {
			// L1 norm, get absolute value of all elements, return the squared
			// root of sum
			case NORM_L1: {
				for (int i = 0; i < src.data.length; i++) {
					sum += (src.data[i] < 0) ? -src.data[i] : src.data[i];
				}
				break;
			}

			// L2 norm, get sum of squared of all elements, return the squared
			// root of sum
			case NORM_L2: {
				for (int i = 0; i < src.data.length; i++) {
					sum += src.data[i] * src.data[i];
				}
				sum = Math.sqrt(sum);
				break;
			}

			default:
				break;
			}
			return sum;
		}
	}
	/**
	 * normalize vector
	 */
	
	public static Mat vectorNormalize(Mat src, NormType normType){
		double sum = norm(src, normType);
		return src.mul(1/sum);
	}
	/**
	 * Calculate distance between two vectors.
	 * For matrix, this is equal to square root of SSE (Sum of Squared Error)
	 * @param src
	 *            input matrix 1.
	 * @param src 
	 * 			  input matrix 2.          
	 * @param normType
	 *            Specify the norm type, which can be NormType.NORM_L1 or
	 *            NormType.NORM_L2 :
	 * @return
	 */
	public static double dist(Mat src1, Mat src2, NormType normType) {
		assert(src1.cols == src2.cols && src1.rows == src2.rows);
		if (src1.isEmpty()) {            // is this proper way?
			return norm(src2, normType); // if src1 is empty , return norm of src2
		}
		if (src2.isEmpty()) {			 // is this proper way?
			return norm(src1, normType); // if src1 is empty , return norm of src2
		}
		double sum = 0.0;
		double diff;
		switch (normType) {
			// L1 norm,  get absolute value of all elements, return the squared
			// root of sum
			case NORM_L1: {
				for (int i = 0; i < src1.data.length; i++) {
					diff = src1.data[i] - src2.data[i];
					sum += diff > 0 ? diff : -diff;
				}
				break;
			}

			// L2 norm, get sum of squared of all elements, return the squared
			// root of sum
			case NORM_L2: {
				for (int i = 0; i < src1.data.length; i++) {
					diff = src1.data[i] - src2.data[i];
					sum += diff * diff;
				}
				sum = Math.sqrt(sum);
				break;
			}

			default:
				break;
		}
		return sum;
	}
	
	
}

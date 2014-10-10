package cmu.core;

public class Mat {

	public int dims; // number of dimensions
	public int rows, cols; // the number of rows and columns
	public double[] data; // matrix data

	/**
	 * Matrix Constructor Shallow copy of another matrix
	 * 
	 * @param m
	 *            Another Mat
	 */
	public Mat(Mat m) {
		this.cols = m.cols;
		this.rows = m.rows;
		this.dims = m.dims;
		this.data = m.data;
	}

	/**
	 * Matrix Constructor Initialize matrix parameters. Matrix memory is not
	 * allocated.
	 * 
	 * @param rows
	 *            number of rows in the matrix
	 * @param cols
	 *            number of columns in the matrix
	 */
	public Mat(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.dims = 2;
	}

	/**
	 * Matrix Constructor Initialize matrix parameters. Allocate matrix memory,
	 * in all elements from an array.
	 * 
	 * @param rows
	 *            number of rows in the matrix
	 * @param cols
	 *            number of columns in the matrix
	 * @param src
	 *            matrix initializes all elements from this array, row by row.
	 */
	public Mat(int rows, int cols, double[] src) {
		this.rows = rows;
		this.cols = cols;
		this.dims = 2;
		if (src == null || src.length == 0) {
			this.create();
		} else {
			assert (rows * cols == src.length);
			this.create(src);
		}
	}

	/**
	 * Allocate memory for matrix
	 */
	public void create() {
		this.data = new double[rows * cols];
	}

	/**
	 * Allocate memory, initialize elements from a given array
	 * 
	 * @param src
	 *            matrix initializes all elements from this array, row by row.
	 */
	public void create(double[] src) {
		this.data = new double[rows * cols];
		assert (data.length == src.length);
		for (int i = 0; i < src.length; i++)
			this.data[i] = src[i];
	}

	/**
	 * Transpose a matrix. This method performs matrix transposition not in the
	 * original matrix but returns a temporary matrix transposition object.
	 * 
	 * @return
	 */
	public Mat t() {
		/*
		 * Slow version, no optimization
		 */
		Mat t = new Mat(this.cols, this.rows);
		t.create();
		int idx = 0;
		int colIdx, rowIdx;
		for (int i = 0; i < data.length; i++) {
			rowIdx = i / t.cols;
			colIdx = i % t.cols;
			idx = colIdx * t.rows + rowIdx;
			t.data[idx] = this.data[i];
		}
		return t;
	}

	/**
	 * Performs an element-wise multiplication or division.
	 * 
	 * @param InputMat
	 * @return
	 */
	public Mat mul(double alpha) {
		for (int i = 0; i < this.data.length; i++) {
			this.data[i] *= alpha;
		}
		return this;
	}

	/**
	 * Computes a dot-product of two vectors.
	 * 
	 * @param m
	 * @return
	 * @throws IllegalAccessException
	 */
	public double dot(Mat src) throws IllegalAccessException {
		if (!this.isEmpty() && !src.isEmpty()) {
			if (this.data.length != src.data.length) {
				throw new IllegalAccessException("Matrices size should be same");
			}
			double sum = 0;
			for (int i = 0; i < this.data.length; i++) {
				sum += this.data[i] * src.data[i];
			}
			return sum;
		} else {
			throw new IllegalAccessException("Matrix has not been initialized");
		}
	}

	/**
	 * Check whether this matrix has elements or not.
	 * 
	 * @return true if the matrix has no elements
	 */
	public boolean isEmpty() {
		if (this.data == null || this.data.length == 0)
			return true;
		return false;
	}

	/**
	 * Access matrix element by indices. This API makes element access easier
	 * but can slow the performance.
	 * 
	 * @return A reference to the specified matrix element
	 */
	public double at(int i, int j) {
		if (!this.isEmpty()) {
			int idx = i * this.rows + j;
			assert (idx < this.data.length);
			/*
			 * if (idx >= this.data.length) { throw new
			 * ArrayIndexOutOfBoundsException( "matrix index out of bounds at "
			 * + i + ", " + j); }
			 */
			return this.data[idx];
		}
		return Double.MIN_VALUE;
	}
	
/*
	public Mat row(int i) {
		if (!this.isEmpty()) {
			int idx = i * this.rows;
			assert (idx < this.data.length);
			Mat ithRow = new Mat(1, this.cols);
			ithRow.data = this.data[idx];
			return ithRow;
		}
		return null;
	}*/

	/**
	 * Clone the matrix Deep copy
	 * 
	 * @return A new matrix which is identical to the orginal one.
	 */
	public Mat clone() {
		return new Mat(this.rows, this.cols, this.data);
	}
}

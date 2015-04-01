package edu.cmu.cmulib.SVD;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Matrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public double[][] mat;
	int col;
	int row;
	public Matrix(String[][] input) {
		int row = input.length;
		int col = input[0].length;
		mat = new double[row][col];
		for (int i = 0; i < row; i ++) {
			for (int j = 0; j < col; j ++) {
				mat[i][j] = Double.parseDouble(input[i][j]);
			}
		}
		this.col = col;
		this.row = row;
	}
	
	public Matrix(double[][] matrix) {
		mat = matrix;
		this.row = matrix.length;
		this.col = matrix[0].length;
	}
	
	public Matrix(int row, int col, double initval) {
		mat = new double[row][col];
		for (int i = 0; i < row; i ++) {
			for (int j = 0; j < col; j++) {
				mat[i][j] = initval;
			}
		}
		this.row = row;
		this.col = col;
	}
	
	public static Matrix getRandMat(int rown, int col) {
		Random rnd = new Random();
		double[][] rndmat = new double[rown][col];
		for (int i = 0; i < rown; i ++) {
			for (int j = 0; j < col; j++) {
				rndmat[i][j] = rnd.nextDouble();
			}
		}	
		return new Matrix(rndmat);
	}
	
	public double get(int i, int j) {
		return mat[i][j];
	}
	
	public static double getDiff(Matrix x, Matrix y) {
		if (x.row != y.row || x.col != y.col) {
			System.err.println("dimension mismatch");
			return 0;
		}
		int row = x.row;
		int col = y.col;
		double res = 0; 
		for (int i = 0; i < row; i ++) {
			for (int j = 0; j < col; j++) {
				double tmp = x.get(i, j) - y.get(i, j);
				res += tmp*tmp;
			}
		}
		return res;
	}
	
	public double[] getRandCol(int rown) {
        Random rnd = new Random();
        double[] L1 = new double[rown];
        for (int i = 0; i < rown; i ++) {
        	L1[i] = rnd.nextDouble();
        }
        return L1;
	}
	
	public void normalize() {
		double total = 0;
		
		for (int i = 0; i < this.row; i ++) {
			for (int j = 0; j < this.col; j++) {
				total += mat[i][j]*mat[i][j];
			}
		}

		total = Math.sqrt(total);
		for (int i = 0; i < this.row; i ++) {
			for (int j = 0; j < this.col; j++) {
				mat[i][j] /= total;
			}
		}
	}
	
	public Matrix getSubMat(int start, int slave_size) {
		double[][] tmp = new double[row][slave_size];
		for (int i = 0; i < this.row; i ++) {
			for (int j = 0; j < slave_size; j++) {
				tmp[i][j] = mat[i][j + start];
			}
		}
		return new Matrix(tmp);
	}
	
	public String firstColToStr() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < this.row; i ++) {
			sb.append(mat[i][0]);
			sb.append(", ");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	public Matrix transpose() {
		double[][] tmp = new double[col][row];
		for (int i = 0; i < this.row; i ++) {
			for (int j = 0; j < this.col; j++) {
				tmp[j][i] = mat[i][j];
			}
		}
		return new Matrix(tmp);
	}
	
	public Matrix multiply(Matrix x) {
		if (this.col != x.row) {
			System.err.println("multiply, wrong dim");
			return null;
		}
		double[][] tmp = new double[this.row][x.col];
		for (int i = 0; i < this.row; i ++) {
			for (int j = 0; j < x.col; j ++) {
				double tmpnum = 0;
				for (int k = 0; k < this.col; k ++) {
					tmpnum += mat[i][k] * x.get(k, j);
				}
				tmp[i][j] = tmpnum;
			}
		}
		return new Matrix(tmp);
	}
	
	public void add(Matrix x) {
		for (int i = 0; i < this.row; i ++) {
			for (int j = 0; j < this.col; j ++) {
				mat[i][j] += x.get(i, j);
			}
		}	
	}
	
	public Matrix minus(Matrix x) {
		double[][] tmp = new double[row][col];
		for (int i = 0; i < this.row; i ++) {
			for (int j = 0; j < this.col; j ++) {
				tmp[i][j] = mat[i][j] = x.get(i, j);
			}
		}
		return new Matrix(tmp);
	}
	
	
	public void writeToFile(String x) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(x)));
		writer.write(this.firstColToStr());
		writer.close();
		return;
	}


}

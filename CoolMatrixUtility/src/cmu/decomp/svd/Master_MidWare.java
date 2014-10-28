package cmu.decomp.svd;

import java.util.LinkedList;
import java.util.Queue;

import cmu.core.Mat;
import cmu.help.Tag;

public class Master_MidWare {
//	public Mat L;
	private Queue<Mat> que = new LinkedList<Mat> ();
	
	public Master_MidWare() {
		this.que = null;
//		System.out.println(this.que.isEmpty());
	}
	
	public boolean push(Mat L) {
//		System.out.println("pushL  " + L.data[0] + "  " + L.data[1]+ "  " + L.data[2]);
		return true;
	}
	
	public boolean push(Tag tag) {
	    int begin = tag.getBegin();
	    int end = tag.getEnd();
	    System.out.println(begin + "  " + end);
		return true;
	}
	
	public Mat pull() {
		double[] test = {1,1,1};
		Mat L = new Mat(3,1,test);
		return L;
//		if (this.que.size()!=0) {
//			return que.poll();
//		} else {
//			return null;
//		}
	}
}

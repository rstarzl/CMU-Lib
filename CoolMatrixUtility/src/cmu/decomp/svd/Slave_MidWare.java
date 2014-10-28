package cmu.decomp.svd;

import cmu.core.Mat;
import cmu.help.Tag;

public class Slave_MidWare {
	
	public boolean push(Mat L) {
		System.out.println(L.data[0] + "  " + L.data[1]);
		return true;
	}
	
	
	public Mat pullL() {
		double[] test = {0.2,0.2,0.2,0.2};
		Mat L = new Mat(4,1,test);
		return L;
	}
	
	public Tag pullTag() {
		Tag tag = new Tag(0,1);
		return tag;
	}
}

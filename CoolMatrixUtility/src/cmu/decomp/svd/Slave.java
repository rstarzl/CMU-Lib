package cmu.decomp.svd;

import cmu.core.Mat;
import cmu.help.Tag;

/**
 * Slave Class is used to identify different slaves.
 * 
 * @param SlaveId
 *          id of Slave
 * @param workspan
 * 		    time between return and send 
 */
public class Slave {
	public int SlaveId;
	public double workspan = Double.MAX_VALUE;
	
	public Slave (int SlaveId, double workspan) {
		this.SlaveId = SlaveId;	
		this.workspan = workspan;
	}
	
	public static void main (String[] args) {
		double[] test = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		int rows = 4;
		int cols = 4;
		Mat score = new Mat(rows, cols ,test);
		Tag tag;
		Mat S, L;
		Slave_MidWare commu = new Slave_MidWare();
		
		Slave_getSplitedMatrix split = new Slave_getSplitedMatrix(score);
		Slave_SVD svd = new Slave_SVD();
		
		tag = commu.pullTag();
		split.setTag(tag);
		S = split.construct();
//		svd.setS(S);
		L = commu.pullL();
		svd.setL(L);
		L = svd.Slave_UpdateL(S);
		commu.push(L);
		
	}
	
}

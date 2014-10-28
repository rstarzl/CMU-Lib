import cmu.core.Mat;
import cmu.decomp.svd.Slave_SVD;
import cmu.decomp.svd.Slave_getSplitedMatrix;
import cmu.help.Tag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class Slave {
	public int SlaveId;
	public double workspan = Double.MAX_VALUE;
	
	public Slave (int SlaveId, double workspan) {
		this.SlaveId = SlaveId;	
		this.workspan = workspan;
	}
	
	public static void main (String[] args) throws IOException {
		double[] test = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		int rows = 4;
		int cols = 4;
		Mat score = new Mat(rows, cols ,test);
		Tag tag;
		Mat S, L;

        String address = InetAddress.getLocalHost().getHostAddress();
        int port = 8000;

        SlaveMiddleWare sdSlave = new SlaveMiddleWare();
        System.out.println(address + " " + port);
        sdSlave.startSlave(address, port);

        /*
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
		*/

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            synchronized (sdSlave.msgs) {
                if (sdSlave.msgs.size() > 0) {
                    sdSlave.sendParameter(sdSlave.msgs.peek().para * 2);
                    sdSlave.msgs.remove();
                }
            }
        }
	}
	
}

import java.util.ArrayList;

//import cmu.core.Mat;
//import cmu.core.MatOp;
//import cmu.decomp.svd.Master_SVD;
//import cmu.decomp.svd.Master_Spliter;
//import cmu.help.Tag;
import java.io.IOException;

public class Master {
	
	public static void main (String[] args) throws IOException {
		double[] test = {1,2,3,4,5,6,7,8,9,10,11,12};
		int rows = 3;
		int cols = 4;
		int slaveNum = 4;
        /*
		Mat score = new Mat(rows, cols ,test);
		Tag tag;
		Mat Like, slaveL;
        */
        MasterMiddleWare commu = new MasterMiddleWare();
        commu.startMaster();

        System.out.println("PPPPPPPPPPPPPPPP");
        /*
		Master_Spliter split = new Master_Spliter(score, slaveNum);
		Master_SVD svd = new Master_SVD(score, slaveNum);
		
		Like = svd.initL();
		slaveL = null;
		do {
			svd.setL(Like);
			commu.push(Like);
			ArrayList<Tag> index = split.split();
			for(int i = 0; i < index.size(); i++) {
				tag = index.get(i);
				commu.push(tag);
			}
			for (int i = 0; i < slaveNum; i++) {
//				do {
					slaveL = commu.pull();
//				} while (slaveL == null);
				svd.update_SVD(slaveL);
			}
			Like = svd.getUpdateL();
			MatOp.vectorNormalize(Like, MatOp.NormType.NORM_L2);
//			System.out.println(Like.data[0] + "  " + Like.data[1]+ "  " + Like.data[2]);
		} while (!svd.isPerformed(Like));		
		System.out.println("final  " + Like.data[0] + "  " + Like.data[1]+ "  " + Like.data[2]);
		*/
        double[] a = {1.1, 2.2, 3.3, 4.4};

        while (a[0] + a[1] + a[2] + a[3] < 100.0) {
            int remain = 4;
            while (commu.slaveNum() != 4) {System.out.print(commu.slaveNum());}
            System.out.println("\n");

            for (int i = 1; i <= slaveNum; i++) {
                commu.sendParameter(i, a[i - 1]);
            }

            while (remain > 0) {
                synchronized (commu.msgs) {
                    if (commu.msgs.size() > 0) {
                        System.out.println(commu.msgs.peek().para);
                        a[commu.msgs.peek().fromId - 1] = commu.msgs.peek().para;
                        commu.msgs.remove();
                        remain--;
                    }
                }
            }

            double sum = a[0] + a[1] + a[2] + a[3];
            System.out.println("sum :" + sum);
        }
	}
}

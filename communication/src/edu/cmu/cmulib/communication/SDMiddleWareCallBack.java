package edu.cmu.cmulib.communication;

/**
 * Created by amaliujia on 14-10-23.
 */
public interface SDMiddleWareCallBack {

    // when a slave returns computational result,
    // MiddleWare will be notified.
    public void salveReturn(int slaveID, int[][] matrix, int m, int n);

}

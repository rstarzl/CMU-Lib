package edu.cmu.cmulib.communication;

import java.io.IOException;

/**
 * Created by amaliujia on 14-10-23.
 */
public class MasterSDMiddleWare implements SDMiddleWareCallBack{
    //
    private MasterNode masterNode;

    /**
     *
     * @throws IOException
     */
    public void startMaster() throws IOException{
        masterNode = new MasterNode();
        masterNode.startListen();
    }

    /**
     *
     * @param matrix
     * @param slaveId
     */
    public void sendMatrix(int slaveId, SDDummyMatrix matrix){
        masterNode.send(slaveId, matrix.getMatrix());
    }

    /**
     *
     * @param slaveID
     * @param max
     * @param m
     * @param n
     */
    public void sendMatrix(int slaveID, int max[][], int m, int n){
       String message = SDMessage.buildMatrix(max, m, n);
       masterNode.send(slaveID, message);
    }

    public void sendParameter(int slaveId, double para) {
        String message = SDMessage.buildParameter(para);
        masterNode.send(slaveId, message);
    }

    /**
     *
     * @return
     */
    public int slaveNum(){
        if(masterNode != null)
            return masterNode.slaveNum();
        else
            return 0;
    }

    /**
     *
     * @param slaveID
     * @param matrix
     * @param m
     * @param n
     * @Override
     */
    /*`
    public void salveReturn(int slaveID, int[][] matrix, int m, int n) {
         System.out.println("Salve  " + slaveID + " has returned matrix");
    }
    */
    public void msgReceived(int nodeID, String msg) {

    }
}

package edu.cmu.cmulib.communication;

import java.io.IOException;

/**
 * Created by amaliujia on 14-10-23.
 */
public class SlaveSDMiddleWare implements SDMiddleWareCallBack{
    //
    private SlaveNode slaveNode;

    /**
     *
     * @param address
     * @param port
     * @throws IOException
     */
    public void startSlave(String address, int port) throws IOException{
        slaveNode = new SlaveNode(address, port, this);
        slaveNode.connect();
    }

    /**
     *
     * @param matrix
     * @param slaveId
     */
    public void sendMatrix(SDDummyMatrix matrix){
        slaveNode.send(matrix.getMatrix());
    }

    /**
     *
     * @param slaveID
     * @param max
     * @param m
     * @param n
     */
    public void sendMatrix(int max[][], int m, int n){
       String message = SDMessage.buildMatrix(max, m, n);
       slaveNode.send(message);
    }

    public void sendParameter(double para) {
        String message = SDMessage.buildParameter(para);
        slaveNode.send(message);
    }

    /**
     *
     * @param slaveID
     * @param matrix
     * @param m
     * @param n
     * @Override
     */

    /*
    public void salveReturn(int slaveID, int[][] matrix, int m, int n) {
         System.out.println("Salve  " + slaveID + " has returned matrix");
    }
    */
    public void msgReceived(int nodeID, String msg) {
        System.out.println("From :" + nodeID + "    " + msg);
    }
}

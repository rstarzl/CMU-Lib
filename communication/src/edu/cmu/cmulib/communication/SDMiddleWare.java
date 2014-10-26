package edu.cmu.cmulib.communication;

import java.io.IOException;

/**
 * Created by amaliujia on 14-10-23.
 */
public class SDMiddleWare implements SDMiddleWareCallBack{
    //
    private MasterNode masterNode;

    //
    private SlaveNode slaveNode;

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
     * @param address
     * @param port
     * @throws IOException
     */
    public void startSlave(String address, int port) throws IOException{
        slaveNode = new SlaveNode(address, port);
        slaveNode.connect();
    }

    /**
     *
     * @param matrix
     * @param slaveId
     */
    public void sendMatrix(SDDummyMatrix matrix, int slaveId){

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
    public void salveReturn(int slaveID, int[][] matrix, int m, int n) {
         System.out.println("Salve  " + slaveID + " has returned matrix");
    }
}

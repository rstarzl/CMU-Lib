//import cmu.core.Mat;
import edu.cmu.cmulib.communication.*;

import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;

public class MasterMiddleWare implements MiddleWare {
    public class MsgItem {
        public int opId;
        //public Mat data;
        public double para;
        public int fromId;

        /*
        public MsgItem(int nId, int nOpId, Mat nData) {
            opId = nOpId;
            data = nData;
            fromId = nId;
        }
        */

        public MsgItem(int nId, int nOpId, double nPara) {
            opId = nOpId;
            para = nPara;
            fromId = nId;
        }
    }
    //
    private MasterNode masterNode;

    public Queue<CommonPacket> packets;
    
    public PacketHandler packetHandler;
    
    public void register(Class<?> clazz, Queue list){
		packetHandler.register(clazz, list);
	}
    
    public MasterMiddleWare() {
    	packets = new LinkedList<CommonPacket>();
    	packetHandler = new PacketHandler();
    }

    public void startMaster() throws IOException{
        masterNode = new MasterNode(this);
        masterNode.startListen();
    }
/*
    public void sendMatrix(int slaveId, DummyMatrix matrix){
        masterNode.send(slaveId, matrix.getMatrix());
    }

    public void sendMatrix(int slaveID, int max[][], int m, int n){
       String message = Message.buildMatrix(max, m, n);
       masterNode.send(slaveID, message);
    }


    public void sendMatrixDouble(int slaveID, double max[][], int m, int n){
        String message = Message.buildMatrixDouble(max, m, n);
        masterNode.send(slaveID, message);
    }

    public void sendParameter(int slaveId, double para) {
        String message = Message.buildParameter(para);
        masterNode.send(slaveId, message);
    } */
    
    public void sendPacket(int id, CommonPacket packet){
        masterNode.sendObject(id, packet);
    }

    public int slaveNum(){
        if(masterNode != null)
            return masterNode.slaveNum();
        else
            return 0;
    }

    public void msgReceived(int nodeId, CommonPacket packet) {
    	packet.setSlaveId(nodeId);
    /*	synchronized (packets) {
            packets.add(packet);
        }*/
    	synchronized(packetHandler){
      
    	    packetHandler.handlePacket(packet.getObject());
    	}
       /* Message toAdd = new Message(msg);

        switch (toAdd.opCode) {
            
            case Macro.getTransferMatrixDOuble:
                double[] oneDimArray = new double[toAdd.matrixIntegerM * toAdd.matrixIntegerN - 1];

                int index = 0;
                for (int p = 0; p < toAdd.matrixIntegerM; p++) {
                    for (int q = 0; q < toAdd.matrixIntegerN; q++) {
                        oneDimArray[index++] = toAdd.matrixDouble[p][q];
                    }
                }

                Mat nMat = new Mat(toAdd.matrixIntegerM, toAdd.matrixIntegerN, oneDimArray);

                MsgItem item = new MsgItem(nodeID, toAdd.opCode, nMat);
                synchronized (msgs) {
                    msgs.add(item);
                }

                break;
           
            case Macro.transferParameter:
                MsgItem para = new MsgItem(nodeID, toAdd.opCode, Double.parseDouble(toAdd.message));
                synchronized (msgs) {
                    msgs.add(para);
                }

                break;
        }
        

        */
    }
}

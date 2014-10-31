//import cmu.core.Mat;
import edu.cmu.cmulib.communication.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class SlaveMiddleWare implements MiddleWare {
    //
    private SlaveNode slaveNode;

    public Queue<CommonPacket> packets;
    
    public PacketHandler packetHandler;

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

    public SlaveMiddleWare() {
    	packets = new LinkedList<CommonPacket>();
    	packetHandler = new PacketHandler();
    }

    public void startSlave(String address, int port) throws IOException{
        slaveNode = new SlaveNode(address, port, this);
        slaveNode.connect();
    }
    
    public void register(Class<?> clazz, Queue list){
		packetHandler.register(clazz, list);
	}
    /*
    public void sendMatrix(DummyMatrix matrix){
        slaveNode.send(matrix.getMatrix());
    }

    public void sendMatrix(int max[][], int m, int n){
       String message = Message.buildMatrix(max, m, n);
       slaveNode.send(message);
    }

    public void sendMatrixDouble(double max[][], int m, int n){
        String message = Message.buildMatrixDouble(max, m, n);
        slaveNode.send(message);
    }

    public void sendParameter(double para) {
        String message = Message.buildParameter(para);
        slaveNode.send(message);
    }*/
    
    public void sendPacket(CommonPacket packet){
    	slaveNode.send(packet);
    }

    public void msgReceived(int nodeID, CommonPacket packet) {
    	synchronized (packets) {
            packets.add(packet);
        }
    	
    	synchronized(packetHandler){
    	    packetHandler.handlePacket(packet.getObject());
    	}
    	
    	
       /* Message toAdd = new Message(msg);

        switch (toAdd.opCode) {
            /*
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
        } */

        System.out.println("on recieved");
    }
}

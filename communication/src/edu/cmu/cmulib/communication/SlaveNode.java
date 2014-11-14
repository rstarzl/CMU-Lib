package edu.cmu.cmulib.communication;

import java.net.*;
import java.io.*;

public class SlaveNode {
    String mName = "";
    Socket socket = null;
    private ObjectOutputStream oos;
    ObjectInputStream ois = null;
    MiddleWare midd =null;

    private String masterAddress;
    private int masterPort;

    public SlaveNode(String nn) {
        mName = nn;
        System.out.println("I'm a SlaveNode - " + mName);
    }

    public SlaveNode(String masterAddress, int masterPort, MiddleWare myMidd){
        this.masterAddress = masterAddress;
        this.masterPort = masterPort;
        this.midd = myMidd;
    }


    public void connect() {
        try {
  //          System.out.println(InetAddress.getLocalHost().getHostAddress());
            socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8000);
            //socket = new Socket(this.masterAddress, this.masterPort);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            new Thread(new SlaveService(ois)).start();
        }catch (Exception ex) {
            System.out.println("Could not find!");
        }
    }

    public void send(CommonPacket packet){
        try {
			oos.writeObject(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void disconnect() {
        try {
            oos.close();
            ois.close();
            socket.close();
        }
        catch (Exception ex) {
            System.out.println("Could not find!");
        }
    }


    private class SlaveService implements Runnable{
        private ObjectInputStream ois;

        public SlaveService(ObjectInputStream in) {
            this.ois = in;
            System.out.println(this.ois==null);
        }

        public void run(){
            CommonPacket packet;
            try{
                System.out.println("slave service started read from master!!!");
                //System.out.println(in.readLine());
                while(true){
                    // TODO: confirm if received string is complete
                    //Message receivedMessage = new Message(fromMaster);
                    //System.out.println("From master: " + fromMaster);
                	packet = (CommonPacket) ois.readObject();
                	//TestClass data = (TestClass) packet.getObject();
                	//Double data = (Double) packet.getObject();
                    midd.msgReceived(-1, packet);

                    // Decide which operation received
                    /*switch (receivedMessage.opCode){
                        case Macro.transferParameter:
                            // TODO(fyraimar) replace fake implement
                            //double received = Double.parseDouble(receivedMessage.message);
                            //send(Message.buildParameter(received * 2));
                            break;
                        case Macro.transferMatrix:
                            // compute

                            //assume computation complete
                            send(Message.buildMatrix(receivedMessage.matrixInteger,
                                                    receivedMessage.matrixIntegerM,
                                                    receivedMessage.matrixIntegerN));
                            break;
                        default:
                            break;
                    }*/
                   // System.out.println(fromMaster);
                }
            }catch(IOException e){
                System.out.println(e.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

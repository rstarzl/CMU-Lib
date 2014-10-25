package edu.cmu.cmulib.communication;

import java.net.*;
import java.io.*;

public class SlaveNode {
    String mName = "";
    Socket socket = null;
    PrintWriter os = null;
    BufferedReader in = null;

    private String masterAddress;
    private int masterPort;

    public SlaveNode(String nn) {
        mName = nn;
        System.out.println("I'm a SlaveNode - " + mName);
    }

    public SlaveNode(String masterAddress, int masterPort){
        this.masterAddress = masterAddress;
        this.masterPort = masterPort;
    }


    public void connect() {
        try {
  //          System.out.println(InetAddress.getLocalHost().getHostAddress());
   //         socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8000);
            socket = new Socket(this.masterAddress, this.masterPort);
            os = new PrintWriter(socket.getOutputStream());
            os.println("Hello master! - from " + mName);
            os.flush();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(new SlaveService(in)).start();
        }catch (Exception ex) {
            System.out.println("Could not find!");
        }
    }

    public void send(String message){
        os.println(message);
        os.flush();
    }

    public void disconnect() {
        try {
            os.close();
            socket.close();
        }
        catch (Exception ex) {
            System.out.println("Could not find!");
        }
    }


    private class SlaveService implements Runnable{
        private BufferedReader in;

        public SlaveService(BufferedReader in) {
            this.in = in;
            System.out.println(this.in==null);
        }

        public void run(){
            String fromMaster;
            try{
                System.out.println("slave service started read from master!!!");
                System.out.println(in.readLine());
                while((fromMaster=in.readLine())!=null){
                    // TODO: confirm if received string is complete
                    SDMessage receivedMessage = new SDMessage();
                    receivedMessage.extractMessage(fromMaster);

                    // Decide which operation received
                    switch (receivedMessage.opCode){
                        case SDMacro.transferParameter:
                            break;
                        case SDMacro.transferMatrix:
                            // compute

                            //assume computation complete
                            send(SDMessage.buildMatrix(receivedMessage.matrixInteger,
                                                    receivedMessage.matrixIntegerM,
                                                    receivedMessage.matrixIntegerN));
                            break;
                        default:
                            break;
                    }
                    System.out.println(fromMaster);
                }
            }catch(IOException e){
                System.out.println(e.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

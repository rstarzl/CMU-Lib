package edu.cmu.cmulib.communication;

import javax.security.auth.callback.Callback;
import java.net.*;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class MasterNode {
    HashMap<Integer, SlaveData> slaveMap;
    int slaveId=1;
    private int port = 8000;
    private ExecutorService executorService;
    private ServerSocket serverSocket;
    private final int POOL_SIZE = 5;

    //private SDMiddleWare middleWare;
    private Callback middleWare;
    // contructor 
    public MasterNode() throws IOException {
        System.out.println("I'm a MasterNode!");
        slaveMap = new HashMap<Integer, SlaveData>();
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
    }

    public MasterNode(Callback aMiddleWare) throws IOException {
        System.out.println("I'm a MasterNode!");
        slaveMap = new HashMap<Integer, SlaveData>();
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
        this.middleWare = aMiddleWare;
    }



    public void startListen () throws IOException {
        new Thread(new ServerService()).start();
        System.out.println("why!!!!??????!");
    }

    public void send(int id, String message){
        SlaveData aSlaveData = slaveMap.get(id);
        aSlaveData.out.println(message);
        aSlaveData.out.flush();
    }

    public int slaveNum(){
        return slaveMap.size();
    }

    private class ServerService implements Runnable{
        public void run(){
            while(true){
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    System.out.println("socket accepted");
                    executorService.execute(new Slave(socket));
                }catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    private class Slave implements Runnable {
        private Socket socket;
        private PrintWriter writer;
        private BufferedReader in;

        public Slave(Socket socket) throws IOException{
            this.socket = socket;
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("socket connected");
        }

        public void run() {
            try {
                handleSocket();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void handleSocket() throws Exception {
            writer.println("Hello Slave.");
            writer.flush();
            String temp;
            SlaveData aSlave = new SlaveData(in, writer);
            slaveId++;
            synchronized(slaveMap){
                slaveMap.put(aSlave.id,aSlave);
            }
            while((temp=in.readLine()) != null){
                if(!temp.equals("eof")){
                 //  middleWare.salveReturn();
                }
                System.out.println(temp);
                if(temp.equals("eof")){
                    System.out.println("it is eof");
                    break;
                }
            }
            writer.close();
            in.close();
            socket.close();
        }

    }
}

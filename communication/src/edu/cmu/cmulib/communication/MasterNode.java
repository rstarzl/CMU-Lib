package edu.cmu.cmulib.communication;

import java.net.*;
import java.util.HashMap;
import java.io.*;

public class MasterNode {
	HashMap<Integer, SlaveData> slaveMap;
	int slaveId=1;
	// contructor 
    public MasterNode() {
        System.out.println("I'm a MasterNode!");
        slaveMap = new HashMap<Integer, SlaveData>();
    }

    public void startListen (int portNum) throws IOException {
    	ServerService server = new ServerService(portNum);
    	server.start();
    	
    	BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
    	String pattern = "^[0-9]*[1-9][0-9]*$";
    	String temp = "";
    	while(true){
    		System.out.print("::: ");
        	temp = input.readLine();
        	
        	if(temp.equals("quit"))
        		break;
        	
        	if(temp.equals(""))
        		continue;
        	String[] arr = temp.split(" ");
        	if(!arr[0].matches(pattern)){
        		System.out.println("*******************************************************\nWrong slaveID\n*******************************************************");
        		continue;
        	}
        	SlaveData aSlave = slaveMap.get(Integer.parseInt(arr[0]));
        	if((aSlave==null)||(arr.length<2))
        		System.out.println("*******************************************************\nInvalid slave ID or wrong argument format\nArgument: slaveId sentence\n*******************************************************");
        	else{
        		aSlave.out.println(arr[1]);        	
        		aSlave.out.flush();
        	}
        }
    	System.exit(1);
    }
    
    
    private class ServerService extends Thread{
    	private ServerSocket socketListener = null;
    	public ServerService(int portNum) {
			try {
				socketListener = new ServerSocket (portNum);
			} catch (IOException e) {
				System.err.println("Fail to open socket of server.");
			}
		}
    	public void run(){
    		while(true){
    			try {    				
    
    				Socket socket = null;
    				
    				while (true) {
    				socket = socketListener.accept();
                    new Thread(new Slave(socket)).start();
    				}

       			}catch (Exception e) {
       				System.out.println(e);
       			}
    		}
    	}
    }

    private class Slave implements Runnable {  
    
       private Socket socket;  
         
       public Slave(Socket socket) {  
          this.socket = socket;  
          System.out.println("socket connected~~~~~~~~~~");
       }  
         
       public void run() {  
          try {  
             handleSocket();  
          } catch (Exception e) {  
             e.printStackTrace();  
          }  
       } 
       
       private void handleSocket() throws Exception {  
    	   PrintWriter writer = new PrintWriter(socket.getOutputStream());  
           BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           writer.println("Hello Slave."); 
           writer.flush();
           String temp;
           SlaveData aSlave = new SlaveData(slaveId,in,writer);
           slaveId++;
           
           synchronized(slaveMap){
        	   slaveMap.put(aSlave.id,aSlave);
           }
           
           while((temp=in.readLine()) != null){
        	   System.out.println(temp);
        	   
        	   if(temp.equals("eof")){
        		   System.out.println("it is eof");
        		   break;
        	   }
           }
       }  
    } 
}

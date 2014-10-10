package edu.cmu.cmulib.communication;

import java.net.*;
import java.io.*;

public class SlaveNode {
    String mName = "";
    Socket socket = null;
    PrintWriter os = null;
    
    public SlaveNode(String nn) {
        mName = nn;
        System.out.println("I'm a SlaveNode - " + mName);
        
    }

    public void connect() {
        try {        	
        	System.out.println(InetAddress.getLocalHost().getHostAddress());
        	socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8000);
        	os = new PrintWriter(socket.getOutputStream());
        	os.println("Hello master! - from " + mName);
        	os.flush();
        	BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        	String line=null;
        	new Thread(new SlaveService(in)).start();
        	
        	while(true){
        		line = buffer.readLine();
        		os.println(line);
        		os.flush();        		
        	}	
        }catch (Exception ex) {
            System.out.println("Could not find!");
        }
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
    				System.out.println(fromMaster);
    			}
    		}catch(IOException e){
    			System.out.println(e.toString());
    		}

    	}
    }
    
}

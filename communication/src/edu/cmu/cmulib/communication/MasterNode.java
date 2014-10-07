package edu.cmu.cmulib.communication;

import java.net.Socket;

//TODO (fyraimar) delete it
import java.net.*;
import java.io.*;


public class MasterNode {
    public MasterNode() {
        System.out.println("I'm a MasterNode!");
    }

    public void startListen(int portNum) {
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());

            ServerSocket server = new ServerSocket(portNum);
            BufferedReader is = null;
            Socket socket = null;

            int i = 9999999;
            while (i > 0) {
                socket = server.accept();

                String line;
                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Slave: " + is.readLine());
            }

            is.close();
            socket.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

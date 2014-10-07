package edu.cmu.cmulib.communication;

//TODO (fyraimar) delete it
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
            //TODO (fyraimar) fake impl
            System.out.println(InetAddress.getLocalHost().getHostAddress());

            socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 8000);
            os = new PrintWriter(socket.getOutputStream());
            os.println("Hello master! - from " + mName);
            os.flush();
        }
        catch (Exception ex) {
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
}

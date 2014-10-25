package edu.cmu.cmulib.communication;

import edu.cmu.cmulib.communication.*;

//TODO (fyraimar) delete it
import java.net.*;
import java.io.*;

public class SlaveNodeTest {
    public static  void main(String[] args) throws IOException {
        SlaveNode slave = new SlaveNode(args[0]);
        slave.connect();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String s = buffer.readLine();
            System.out.println("before send");
            slave.send(" slave send method test ");
            if(s.equals("end"))
                break;
        }
        System.out.println("......end of main");

    }
}

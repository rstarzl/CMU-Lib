package edu.cmu.cmulib.communication;

//TODO (fyraimar) delete it
import java.io.*;

public class MasterNodeTest {
    public static  void main(String[] args) throws IOException {
        MasterNode mn = new MasterNode();
        mn.startListen();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String s = buffer.readLine();
            System.out.println("before send");
            mn.send(1," how are u? ");
            if(s.equals("end"))
                break;
        }
        System.out.println("......end of main");
    }
}

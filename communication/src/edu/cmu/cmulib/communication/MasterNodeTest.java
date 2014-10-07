import edu.cmu.cmulib.communication.*;

import java.net.InetAddress;
import java.net.Socket;

//TODO (fyraimar) delete it
import java.net.*;
import java.io.*;

public class MasterNodeTest {
    public static  void main(String[] args) {
        MasterNode mn = new MasterNode();
        mn.startListen(8000);
    }
}

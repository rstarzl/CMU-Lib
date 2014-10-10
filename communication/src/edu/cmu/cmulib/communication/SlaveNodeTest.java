import edu.cmu.cmulib.communication.*;

import java.net.InetAddress;
import java.net.Socket;

//TODO (fyraimar) delete it
import java.net.*;
import java.io.*;

public class SlaveNodeTest {
    public static  void main(String[] args) {
        SlaveNode slave = new SlaveNode(args[0]);
        slave.connect();
    }

}

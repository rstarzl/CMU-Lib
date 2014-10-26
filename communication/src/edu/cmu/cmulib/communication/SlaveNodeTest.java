import edu.cmu.cmulib.communication.*;

//TODO (fyraimar) delete it
import java.net.*;
import java.io.*;

public class SlaveNodeTest {
    public static  void main(String[] args) throws IOException {
        String address = InetAddress.getLocalHost().getHostAddress();
        int port = 8000;

        SlaveSDMiddleWare sdSlave = new SlaveSDMiddleWare();
        sdSlave.startSlave(address, port);

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String s = buffer.readLine();
            System.out.println("before send");
            if(s.equals("end"))
                break;
        }
        System.out.println("......end of main");

    }
}

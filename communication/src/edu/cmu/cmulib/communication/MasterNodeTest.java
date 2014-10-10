import edu.cmu.cmulib.communication.*;

//TODO (fyraimar) delete it
import java.net.*;
import java.io.*;

public class MasterNodeTest {
    public static  void main(String[] args) throws IOException {
    	
    	MasterNode mn = new MasterNode();
    	mn.startListen(8000);
    	System.out.println("Master Serivce Ended");
		//System.exit(0);
    	
        
    }
}

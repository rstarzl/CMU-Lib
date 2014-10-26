import edu.cmu.cmulib.communication.*;

//TODO (fyraimar) delete it
import java.io.*;

public class MasterNodeTest {
    public static  void main(String[] args) throws IOException {
        MasterSDMiddleWare commu = new MasterSDMiddleWare();
        commu.startMaster();

        System.out.println(commu.slaveNum());

        
        //BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        while (commu.slaveNum() != 4) {}
        commu.sendParameter(1, 1.1);
        commu.sendParameter(2, 2.2);
        commu.sendParameter(3, 3.3);
        commu.sendParameter(4, 4.4);
        
        while(true){
            //String s = buffer.readLine();
            //System.out.println("before send");
            //mn.send(0," how are u? ");
            //System.out.println(commu.slaveNum());
            //if(s.equals("end"))
            //    break;
        }
        //System.out.println("......end of main");
    }
}

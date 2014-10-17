package edu.cmu.cmulib.communication;

/**
 * Created by amaliujia on 14-10-16.
 */
public class SDMessage {
    public int opCode;
    public String message;

    public static String buildParameter(double parameter){
        String parameterString = parameter + "";
        int opCode;
        opCode = SDMacro.transferParameter;
        return "" + opCode + "\t" + parameterString;
    }

   // public double

    public void extractMessage(String message) throws Exception{
        String[] content= message.split("\\t");
        if(content.length <= 0){
            throw new Exception("Wrong message! Message should at least contain a opcode");
        }
        this.opCode = Integer.parseInt(content[0]);

        switch (this.opCode){
            case SDMacro.transferParameter:
                if(content.length <= 1){
                    throw new Exception("Wrong message! Parameter message should at least contain two elements");
                }
                this.message = content[1];
                break;
            default:
                break;
        }
        return;
    }
}

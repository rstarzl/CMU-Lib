package edu.cmu.cmulib.communication;

/**
 * Created by amaliujia on 14-10-16.
 */
public class SDMessage {
    public int opCode;
    public String message;
    public int matrixInteger[][];
    public int matrixIntegerM;
    public int matrixIntegerN;

    public static String buildParameter(double parameter){
        String parameterString = String.valueOf(parameter);
        int opCode;
        opCode = SDMacro.transferParameter;
        return opCode + "\t" + parameterString;
    }

    public static String buildMatrix(int max[][], int m, int n){
        int opCode = SDMacro.transferMatrix;
        byte[] output = new byte[m * n * 4];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                output[i * n * 4 + j * 4] = (byte)(max[i][j] & 0xff);
                output[i * n * 4 + j * 4 + 1] = (byte)((max[i][j] & 0xff00) >> 8);
                output[i * n * 4 + j * 4 + 2] = (byte)((max[i][j] & 0xff0000) >> 16);
                output[i * n * 4 + j * 4 + 3] = (byte)((max[i][j] & 0xff000000) >> 24);
            }
        }
        String message = new String(output);
        return opCode + "\t" + m + "\t" + n + "\t" + message;

    }

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
            case SDMacro.transferMatrix:
                if(content.length <= 4){
                    throw new Exception("Wrong message! Matrix message should at least contain four elements");
                }
                int m = Integer.parseInt(content[1]);
                int n = Integer.parseInt(content[2]);
                matrixIntegerM = m;
                matrixIntegerN = n;
                for(int i = 0; i < m; i++){
                    for(int j = 0; j < n; j++){
                       int temp = 0;
                       byte b1 = (byte) content[3].charAt(i * m * 4 + j * 4);
                       byte b2 = (byte) content[3].charAt(i * m * 4 + j * 4 + 1);
                       byte b3 = (byte) content[3].charAt(i * m * 4 + j * 4 + 2);
                       byte b4 = (byte) content[3].charAt(i * m * 4 + j * 4 + 3);
                       temp |= b1;
                       temp <<= 8;
                       temp |= b2;
                       temp <<= 8;
                       temp |= b3;
                       temp <<= 8;
                       temp |= b4;
                       matrixInteger[i][j] = temp;
                    }
                }
                break;
            default:
                break;
        }
        return;
    }
}

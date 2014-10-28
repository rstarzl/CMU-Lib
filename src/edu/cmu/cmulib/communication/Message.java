package edu.cmu.cmulib.communication;

/**
 * Created by amaliujia on 14-10-16.
 */
public class Message {
    public int opCode;
    public String message;
    public int matrixInteger[][];
    public double matrixDouble[][];
    public int matrixIntegerM;
    public int matrixIntegerN;

    public Message(String msg) {
        try {
            extractMessage(msg);
        }
        catch (Exception ex) {
        }
    }

    public static String buildParameter(double parameter){
        String parameterString = String.valueOf(parameter);
        int opCode;
        opCode = Macro.transferParameter;
        return opCode + "\t" + parameterString;
    }

    public static String buildMatrix(int max[][], int m, int n){
        int opCode = Macro.transferMatrix;
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

    public static String buildMatrixDouble(double max[][], int m, int n){
        int opCode = Macro.getTransferMatrixDOuble;
        byte[] output = new byte[m * n * 8];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                double temp = max[i][j];
                long c = Double.doubleToLongBits(temp);
                output[i * n * 8 + j * 8] = (byte)(0xff & c);
                output[i * n * 8 + j * 8 + 1] = (byte)((byte)(c >> 8) & 0xff);
                output[i * n * 8 + j * 8 + 2] = (byte)((c & 0xff0000) >> 16);
                output[i * n * 8 + j * 8 + 3] = (byte)((c & 0xff000000) >> 24);
                output[i * n * 8 + j * 8 + 4] = (byte)((c  >> 32) &  0xff);
                output[i * n * 8 + j * 8 + 5] = (byte)((c  >> 40) &  0xff);
                output[i * n * 8 + j * 8 + 6] = (byte)((c  >> 48) &  0xff);
                output[i * n * 8 + j * 8 + 7] = (byte)((c  >> 56) &  0xff);
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
            case Macro.transferParameter:
                if(content.length <= 1){
                    throw new Exception("Wrong message! Parameter message should at least contain two elements");
                }
                this.message = content[1];
                break;
            case Macro.transferMatrix:
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
            case Macro.getTransferMatrixDOuble:
                if(content.length <= 4){
                    throw new Exception("Wrong message! Double Matrix message should at least contain four elements");
                }
                int dm = Integer.parseInt(content[1]);
                int dn = Integer.parseInt(content[2]);
                matrixIntegerM = dm;
                matrixIntegerN = dn;
                for(int i = 0; i < dm; i++){
                    for(int j = 0; j < dn; j++){
                        long temp = 0;
                        byte b1 = (byte) content[3].charAt(i * dm * 8 + j * 8);
                        byte b2 = (byte) content[3].charAt(i * dm * 8 + j * 8 + 1);
                        byte b3 = (byte) content[3].charAt(i * dm * 8 + j * 8 + 2);
                        byte b4 = (byte) content[3].charAt(i * dm * 8 + j * 8 + 3);
                        byte b5 = (byte) content[3].charAt(i * dm * 8 + j * 8 + 4);
                        byte b6 = (byte) content[3].charAt(i * dm * 8 + j * 8 + 5);
                        byte b7 = (byte) content[3].charAt(i * dm * 8 + j * 8 + 6);
                        byte b8 = (byte) content[3].charAt(i * dm * 8 + j * 8 + 7);
                        temp |= b1;
                        temp <<= 8;
                        temp |= b2;
                        temp <<= 8;
                        temp |= b3;
                        temp <<= 8;
                        temp |= b4;
                        temp <<= 8;
                        temp |= b5;
                        temp <<= 8;
                        temp |= b6;
                        temp <<= 8;
                        temp |= b7;
                        temp <<= 8;
                        temp |= b8;
                        matrixDouble[i][j] = Double.longBitsToDouble(temp);
                    }
                }
                break;
            default:
                break;
        }
        return;
    }
}

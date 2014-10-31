package edu.cmu.cmulib.communication;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SlaveData {
    /* store output and input steam of a slave */
    public int id;
    //public BufferedReader in;
    //public PrintWriter out;
    public String slaveAddress;
    public int slavePort;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    public SlaveData(ObjectInputStream ois,ObjectOutputStream oos) {
        this.ois = ois;
        this.oos = oos;
    }
}

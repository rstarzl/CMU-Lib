package edu.cmu.cmulib.communication;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class SlaveData {
    /* store output and input steam of a slave */
    public int id;
    public BufferedReader in;
    public PrintWriter out;
    public String slaveAddress;
    public int slavePort;

    public SlaveData(BufferedReader in,PrintWriter out) {
        this.in = in;
        this.out = out;
    }
}

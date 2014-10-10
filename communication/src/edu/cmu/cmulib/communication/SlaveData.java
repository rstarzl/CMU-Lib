package edu.cmu.cmulib.communication;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class SlaveData {
	
		/* store output and input steam of a slave */
		public int id;
		public BufferedReader in;
		public PrintWriter out;
		
		public SlaveData(int id,BufferedReader in,PrintWriter out) {
			this.in = in;
			this.out = out;
			this.id = id;
		}
}

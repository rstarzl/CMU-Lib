package cmu.help;

import java.io.Serializable;

public class Tag implements Serializable{
	public int begin;
	public int end;
	
	public Tag (int begin, int end) {
		this.begin = begin;
		this.end = end;
	}
	
	public int getBegin() {
		return this.begin; 
	}
	
	public int getEnd() {
		return this.end; 
	}
}

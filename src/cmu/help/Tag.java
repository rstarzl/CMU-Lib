package cmu.help;

public class Tag {
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

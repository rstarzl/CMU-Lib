package edu.cmu.cmulib.communication;

import java.io.Serializable;

/*
 * 
 */

public class CommonPacket implements Serializable {
	/**
	 * 
	 */
    private static final long serialVersionUID = 4L;
    int slaveId;
    String classType;
    Object object;
	
    public CommonPacket(int id, String type, Object obj){
	    slaveId = id;
	    type = classType;
	    object = obj;
	}
	
    public CommonPacket(int id, Object obj){
        slaveId = id;
        object = obj;
	}
	
    public Object getObject(){
        return object;
	}
    
    public int getSlaveId(){
    	return slaveId;
    }
    
    public void setSlaveId(int id){
    	slaveId = id;
    }

}
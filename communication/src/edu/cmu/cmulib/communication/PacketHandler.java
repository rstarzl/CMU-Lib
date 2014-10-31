package edu.cmu.cmulib.communication;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
public class PacketHandler {
	@SuppressWarnings("rawtypes")
	HashMap<Class<?>, Queue> map = new HashMap<Class<?>,Queue>();
	
	public void register(Class<?> clazz, Queue list){
		map.put(clazz, list);
    }
	@SuppressWarnings("unchecked")
	public void handlePacket(Object obj){
		if(map.containsKey(obj.getClass())){
			
            //synchronized(map.get(obj.getClass())){
			    map.get(obj.getClass()).add(obj);
			   // System.out.println((Double)obj);
		   // }
		}
    }
	
	public static void main(String[] args){
		LinkedList<String> sList = new LinkedList<String>();
		LinkedList<Double> dList = new LinkedList<Double>();
		PacketHandler p = new PacketHandler();
		p.register(String.class,sList );
		p.register(Double.class,dList );
		p.handlePacket("abcde");
		p.handlePacket(1.25);
		System.out.println(sList.get(0));
		System.out.println(dList.get(0));
		
		
	}
	
	
}

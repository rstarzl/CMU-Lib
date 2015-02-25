package edu.cmu.cmulib.network;

import org.netlib.util.booleanW;
import org.netlib.util.intW;

import cmu.decomp.svd.Master_Spliter;
import sun.security.jca.GetInstance;

/**
 * @author      Qichen Pan <pqichen@andrew.cmu.edu>
 * @version     1.0         
 * @since       2015-02-24          
 */
public class NodeManager {
	/**
	 * NodeManager is a singleton in Network API layer.
	 * It handles all requests on getting/setting nodes in the network.
	 */
	
	private static NodeManager nodeMng = new NodeManager();
	
	/**
	 * A private constructor can prevent instantiating 
	 */
	private NodeManager(){}
	
	/**
	 * Static method to get the unique instance of the class.
	 * 
	 * @return the unique instance of NodeManager class.
	 */
	public static NodeManager getInstance()
	{
		return nodeMng;
	}
	
	/**
	 * A static method that get the current number of nodes
	 * in system.
	 * 
	 * @return number of nodes in system.
	 */
	protected static int getNumOfNodes()
	{
		//TODO need support method on server side 
		return Master.getNumOfNodes();
	}
	
	/**
	 * A static method that can get a list of IP addresses of
	 * active nodes in system.
	 * 
	 * @return an array of Strings, each of which is an IP
	 * 		   address of a certain node in system.
	 */
	protected static String [] getNodeIPList()
	{
		//TODO need support method on server side 
		return Master.getNodeIPs();
	}
	
	/**
	 * A static method that can get information of a certain node.
	 * Information includes CPU type, Memory available, IP address,
	 * MAC, port, etc.
	 * 
	 * @param IPAddr, the IP address of a certain node.
	 * 
	 * @return a String that contains information regarding to the
	 * 		   node
	 */
	protected static String getCertainNodeInfo(String IPAddr)
	{
		//TODO need support method on server side 
		return Master.getNodeInfo(IPAddr);
	}
	
	/**
	 * A static method that returns information of all nodes in 
	 * system
	 * 
	 * @return an array of Strings that contain information of
	 * 		   each node in system.
	 */
	protected static String [] getAllNodesInfo()
	{
		String [] IPList = getNodeIPList();
		if (IPList.length == 0)
		{
			return null;
		} 

		String [] nodesInfo = new String[IPList.length];
		for (int i = 0; i < IPList.length; i++)
		{
			nodesInfo[i] = getCertainNodeInfo(IPList[i]);
		}
		return nodesInfo;
	}
	
	/**
	 * A static method that can add a new node into system
	 * 
	 * @param MAC, the MAC address of the new node
	 * 
	 * @param IPAddr, the IP address of the new node
	 * 
	 * @param port, the port that the connection will be established 
	 * 		  on
	 * 
	 * @return a boolean value representing if the job is successfully
	 *         done
	 */
	protected boolean addNode(String MAC, String IPAddr, int port)
	{
		//TODO need support method on server side 
		return Master.addNode(MAC, IPAddr, port);
	}
}

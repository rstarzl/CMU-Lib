package edu.cmu.cmulib.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * @author      Ying Sheng <yingshen@andrew.cmu.edu>
 * @version     1.0         
 * @since       2015-02-24          
 */
public class iniService {
	
	private Map<String, String> params = new HashMap<String, String>();
	//TODO: put a real default file here
	private final String defaultFile = "<Somepath_to_default_file>";
	/**
	 * setup default server parameter from file  
	 * <p>
	 * The method parse the default configuration file and setup corresponding
	 * parameters.Each line in the file contains one parameter setting using format:
	 * "ParamName=ParamValue"
	 * <p>
	 *
	 * @param  
	 * @return boolean value, return true when config is successful
	 * @throws FileNotFoundException 
	 */
	public boolean setDefault() throws FileNotFoundException {
		Scanner scan = new Scanner(new File(defaultFile));
		String line;
	    do {
	      line = scan.nextLine();
	      String[] pair = line.split("=");
	      if (!this.params.containsKey(pair[0].trim())) {
	    	  this.params.put(pair[0].trim(), pair[1].trim());
	      }
	    } while (scan.hasNext());
	    scan.close();	    
	    return true;
	}
	/**
	 * setup server parameter from file  
	 * <p>
	 * The method parse the configuration file and setup corresponding
	 * parameters for user. Any non specified parameter will have default value
	 * Each line in the file contains one parameter setting using format:
	 * "ParamName=ParamValue"
	 * <p>
	 *
	 * @param  configFile
	 * @return boolean value, return true when config is successful
	 * @throws FileNotFoundException 
	 */
	
	public boolean setFromFile(String configFile) throws FileNotFoundException {
		// need to call setDefault before call this method
		setDefault();
		
		// read from user config file
	    Scanner scan = new Scanner(new File(configFile));
	    String line = null;
	    do {
	      line = scan.nextLine();
	      String[] pair = line.split("=");
	      if (!params.containsKey(pair[0].trim())) {
	    	  // illegal input
	    	  System.err.println("Wrong parameter name in configFile: " + configFile);
	    	  continue;
	      }
	      params.put(pair[0].trim(), pair[1].trim());
	    } while (scan.hasNext());
	    scan.close();
	   
	    //TODO need support method on server side 
	    for (String paramName: params.keySet()) {
	    	switch(paramName) {
	    		case "NumOfNodes":
	    			Master.setNumOfNodes(Integer.parseInt(params.get(paramName)));
	    			break;
	    		case "MaxRAM":
	    			Master.setMaxRam(Integer.parseInt(params.get(paramName)));
	    			break;
	    		case "TimeOut":
	    			Master.setTimeOut(Integer.parseInt(params.get(paramName)));
	    			break;
	    		case "Redundency": //# of replicas
	    			Master.setRedundency(Integer.parseInt(params.get(paramName)));
	    			break;
	    		case "ChunkSize":
	    			Master.setChunkSize(Integer.parseInt(params.get(paramName)));
	    			break;
	    		default:
	    			break;
	    	}
	    }
	    
		return true;
	}


}

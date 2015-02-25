package edu.cmu.cmulib.API.ml;
import java.util.HashMap;
import java.util.Map;

/**
 * @author      Yi Song, Mengda Yang
 *
 * @since       2015-02-24          
 */


public class SVD extends MLAlgo {
	
	//private Map<String, String> parameters = new HashMap<String, String>();

	private int slaveNum;
	
	
	private Data data;
	private int rows;
	private int cols;
	
	private int port;

	
	@Override
	String showAlgoName() {
		return "SVD";
	}

	@Override
	Boolean setParameters(Map params) {
		this.slaveNum = Integer.parseInt((String) params.get("slaveNum"));
		this.rows = Integer.parseInt((String) params.get("rows"));
		this.cols = Integer.parseInt((String) params.get("cols"));
		this.port = Integer.parseInt((String) params.get("port"));
		return null;
	}

	@Override
	Boolean getData(Data data) {
		// TODO Auto-generated method stub
		this.data = data;
		return true;
	}
	
	/**
     * request a method from dummy method
     * 
     * runDummyMaster(int slaveNum, int rows, int cols, double[][] data, int port)
	 *
     */

	@Override
	Data runAlgo() {

		double [][] cleanData =  data.parseData();
		return null;
		
		//return runDummyMaster(slaveNum, rows, cols, cleanData, port);

				
	}

}

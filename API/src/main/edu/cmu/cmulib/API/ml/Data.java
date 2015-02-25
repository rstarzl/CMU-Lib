package edu.cmu.cmulib.API.ml;

/**
 * @author      Yi Song, Mengda Yang
 *
 */
public class Data {

	String delimiter;
    String dataType;
 	int numOfRows;
 	int numOfColumns;
    String srcDataFile;
	
	Data(int numOfRows, int numOfColumns,String srcDataFile, String delimiter,
			String dataType){
		
		this.numOfRows = numOfRows;
		this.numOfColumns = numOfColumns;
		
		this.srcDataFile = srcDataFile;
		this.delimiter = delimiter;
		this.dataType = dataType;
	}
	// cast data to double
	double[][] parseData(){
		
		//fake data 
		double[][] data ={
			    {1,2,3},
			    {4,5,6},
			    {7,8,9},
			    };
		return data;
	}
	
}

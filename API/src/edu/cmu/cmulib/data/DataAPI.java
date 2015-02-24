package edu.cmu.cmulib.data;

import java.io.BufferedReader;
import java.io.FileReader;

class DataAPI{
    
    //Passes data to SVD (assuming SVD's Master contains getData method)
    public static Boolean passingData(int numOfRows, int numOfColumns, Boolean sentByRow, String srcDataFile, String Delim, String dataType) throws Exception {
        
        //Assuming number of slaves for now
        int numOfSlaves = 4;
        
        //Reading data from file
        BufferedReader br = new BufferedReader(new FileReader(srcDataFile));
        String line = br.readLine();
        String[] toks = new String[numOfColumns * numOfRows];
        
        //Stores data row by row
        if(sentByRow){
            
            int i =0;
            while(line!=null)
            {
                String[] tok = line.split(Delim);
                for(String t:tok){
                    toks[i] = t;
                    i++;
                }
                line = br.readLine();
            }
        }
        else{
            //Stores data column by column
            int i = 0;
            while(line!=null){
                String[] tok = line.split(Delim);
                
                for(int j=0; j<tok.length; j++){
                    toks[numOfRows*j + i] = tok[j];
                }
                i++;
                line = br.readLine();
            }
       
        }
        
        br.close();
        
        //Sends data to Master's getData method and returns true if it was received successfully. Else, false.
        if (Master.getData(numSlaves,toks,numOfRows,numOfColumns,sentByRow,dataType) )
            return true;
        else
            return false;
        

    }
    
    
    
}

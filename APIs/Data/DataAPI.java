import edu.cmu.cmulib.DummyMaster.src.Master;
import java.io.BufferedReader;
import java.io.FileReader;

class DataAPI{
    
    
    public static Boolean passingData(int numOfRows, int numOfColumns, Boolean sentByRow, String srcDataFile, String Delim, String dataType) throws Exception {
        
        //Assuming number of slaves for now
        int numOfSlaves = 4;
        
        BufferedReader br = new BufferedReader(new FileReader(srcDataFile));
        String line = br.readLine();
        String[] toks = new String[numOfColumns + numOfRows];
        
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
            //Transpose the matrix
            String[][] temp = new String[numOfColumns][numOfRows];
            int i = 0;
            while(line!=null){
                String[] tok = line.split(Delim);
                for(int j=0; j>tok.length; j++){
                    temp[j][i] = tok[j];
                }
                i++;
                line = br.readLine();
            }
            int k = 0;
            for(i=0;i<temp.length;i++){
                for(int j=0;j<temp[i].length;j++){
                    toks[k] = temp[i][j];
                }
                k++;
            }
            
        }
        
        br.close();
        
        if (Master.getData(numSlaves,toks,numOfRows,numOfColumns,sentByRow,dataType) )
            return true;
        else
            return false;
        

    }
    
    
    
}

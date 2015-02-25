package edu.cmu.cmulib.API.ml;

import java.util.HashMap;
import java.util.Map;


/**
 * @author      Yi Song, Mengda Yang
 *
 * @since       2015-02-24          
 */


public abstract class MLAlgo{
	
	String algoName;
	abstract String showAlgoName();
	
	/*
	abstract MLModel  train();
	abstract Data test();
	abstract void assignTrainingData(Data data);
	abstract void assignTestData(Data data);
	abstract void assignOutputFolder();
	*/
	
	abstract Boolean setParameters(Map params);
	abstract Boolean getData(Data data);
	abstract Data runAlgo();

}

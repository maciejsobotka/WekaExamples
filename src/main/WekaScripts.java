package main;

import scripts.GainRatioEvalScript;

/** 
* @author Maciej Sobótka
*/

public class WekaScripts {

	public static void main(String[] args){
		// lab1
		/*
		PreprocessScript ps = new PreprocessScript();
		try {
			ps.PreprocessData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		// lab2
		GainRatioEvalScript sas = new GainRatioEvalScript();
		try {
			sas.GainRatioAttributeEval("195420L3_1a.arff", 0.001);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

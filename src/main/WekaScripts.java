package main;

import scripts.SelectAttrScript;

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
		SelectAttrScript sas = new SelectAttrScript();
		try {
			sas.GainRatioAttributeEval("195420L3_1a.arff");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

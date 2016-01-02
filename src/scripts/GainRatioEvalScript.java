package scripts;

import java.util.Arrays;

import data.GainRatioAttr;
import utils.LoadInstances;
import weka.core.Instances;

public class GainRatioEvalScript {
	private Instances data;
	private int numInstances;
	
	public void GainRatioAttributeEval(String fileName, double threshold) throws Exception{

		LoadInstances loader = new LoadInstances();
		data = loader.Load(fileName);
		numInstances = data.numInstances();
		int classIndex = data.classIndex();
		
		GainRatioAttr[] rank = new GainRatioAttr[data.numAttributes()-1];
		int n=0;
		for(int i=0; i<rank.length; ++i)
			if (i != classIndex){
				rank[n] = new GainRatioAttr(getGainRatio(i,classIndex),i+1,data.attribute(i).name());
				n++;
			}
		Arrays.sort(rank);
		System.out.println("\tGain Ratio feature evaluator\n\nRanked attributes:");
		n=0;
		for(GainRatioAttr i : rank)
			if (i.getGainRatioVal() > threshold){
				System.out.println(i);
				n++;
			}
		
		System.out.print("\nSelected attributes: ");
		for(int i = 0; i < n; ++i)
			if (i != n - 1) System.out.print(rank[i].getAttrNumber() + ",");
			else System.out.print(rank[i].getAttrNumber() + " : " + n);
	}
	
	private double getGainRatio(int attrIndex, int classIndex){

		double h1 = getH(classIndex);
		double h2 = getConH(attrIndex,classIndex);
		double h3 = getH(attrIndex);
		if (h1 - h2 == 0.0) return 0.0;
		else return (h1-h2)/h3;
	}
	
	private double getH(int attrIndex){
		double h = 0.0;
		int numVal = data.attribute(attrIndex).numValues();
		int[] attrVal = new int[numVal];
		
		for(int i=data.numInstances()-1; i>=0; i--){
			int j = (int)data.get(i).value(attrIndex);
			attrVal[j]++;
		}
		for(int i = 0; i < numVal; ++i)
			h+=((double)attrVal[i]/numInstances)*(Math.log((double)numInstances/attrVal[i])/Math.log(2));
		
		return h;
	}
	
	private double getConH(int attrIndex, int classIndex){
		double h = 0.0;
		int classNumVal = data.attribute(classIndex).numValues();
		int attrNumVal = data.attribute(attrIndex).numValues();
		int[] attrVal = new int[attrNumVal];
		int[][] classAttrVal = new int[classNumVal][attrNumVal];
		 
		for(int i=numInstances-1; i>=0; i--){
			int j = (int)data.get(i).value(classIndex);
			int k = (int)data.get(i).value(attrIndex);
			attrVal[k]++;
			classAttrVal[j][k]++;
		}
		
		for(int i=0; i<classNumVal; ++i)
			for(int j=0; j<attrNumVal; ++j)
				if(classAttrVal[i][j]!=0)
					h+=((double)classAttrVal[i][j]/numInstances)*(Math.log((double)attrVal[j]/classAttrVal[i][j])/Math.log(2));
	
		return h;
	}
	
}

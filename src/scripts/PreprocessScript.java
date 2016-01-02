package scripts;

import utils.LoadInstances;
import utils.SaveInstances;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.instance.RemoveWithValues;

/** 
* @author Maciej Sobótka
*/

public class PreprocessScript {
	public void PreprocessData() throws Exception{
		LoadInstances loader = new LoadInstances();
		Instances data = loader.Load("195420L2_2.arff");

		 for(int i=data.numInstances()-1; i>=0; i--){
			 if(data.get(i).value(0)==4)
				 data.delete(i);
		 }
		 RemoveWithValues filter = new RemoveWithValues();
		 String[] options = new String[5];
		 options[0] = "-C";    // attribute index
		 options[1] = "13";
		 options[2] = "-S";    // match if value is smaller than
		 options[3] = "900";
		 options[4] = "-V";    // now if higher than
		 filter.setOptions(options);

		 filter.setInputFormat(data);
		 data = Filter.useFilter(data, filter);
		 
		 Remove filter2 = new Remove();
		 String[] options2 = new String[2];
		 options2[0] = "-R";
		 options2[1] = "1"; 
		 filter2.setOptions(options2);

		 filter2.setInputFormat(data);
		 data = Filter.useFilter(data, filter2);
		 SaveInstances saver = new SaveInstances();
		 saver.Save(data, "195420L3_2.arff");
		 System.out.println("done");
	}
}

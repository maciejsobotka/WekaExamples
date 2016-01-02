package data;

public class GainRatioAttr implements Comparable<GainRatioAttr>{
	private double gainRatioVal;
	private int attrNumber;
	private String attrName;
	
	public GainRatioAttr(double gainRatioVal, int attrNumber, String attrName){
		this.gainRatioVal = gainRatioVal;
		this.attrNumber = attrNumber;
		this.attrName = attrName;
	}
	
	public double getGainRatioVal() {
		return gainRatioVal;
	}

	public int getAttrNumber() {
		return attrNumber;
	}

	public String getAttrName() {
		return attrName;
	}

	@Override
	public int compareTo(GainRatioAttr o) {
		return -Double.compare(this.getGainRatioVal(), o.getGainRatioVal());
	}

	@Override
	public String toString() {
		return "" + gainRatioVal + '\t' + attrNumber + ' ' + attrName;
	}
}

package it.univipm.Gambini.Ragaini.Prog_OOP_CER.model;

public class Data {
	private char freq;
	private String geo;
	private String unit;
	private String objective;
	private Double[] timePeriod;
	
	public Data(char freq, String geo, String unit, String objective, Double[] timePeriod) {
		this.freq = freq;
		this.geo = geo;
		this.unit = unit;
		this.objective = objective;
		this.timePeriod = timePeriod;
	}
	
	public Data() {
	}

	public char getFreq() {
		return freq;
	}
	public void setFreq(char freq) {
		this.freq = freq;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getObjective() {
		return objective;
	}
	public Double[] gettimePeriod() {
		return timePeriod;
	}
	public void settimePeriod(Double[] timePeriod) {
		this.timePeriod = timePeriod;
	}

}

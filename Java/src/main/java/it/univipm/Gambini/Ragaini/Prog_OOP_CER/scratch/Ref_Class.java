package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;

public class Ref_Class {
	private char freq;
	private String geo;
	private String unit;
	private Integer objective;
	private Integer[] timePeriod;
	
	public Ref_Class(char freq, String geo, String unit, Integer objective, Integer[] timePeriod) {
		super();
		this.freq = freq;
		this.geo = geo;
		this.unit = unit;
		this.objective = objective;
		this.timePeriod = timePeriod;
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
	public Integer getObjective() {
		return objective;
	}
	public void setObjective(Integer objective) {
		this.objective = objective;
	}
	public Integer[] getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(Integer[] timePeriod) {
		this.timePeriod = timePeriod;
	}
	
	
}

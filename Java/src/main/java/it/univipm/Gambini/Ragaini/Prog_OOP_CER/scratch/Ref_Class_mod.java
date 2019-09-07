package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;

public class Ref_Class_mod {
	private char freq;
	private String geo;
	private String unit;
	private Double[] objective_timePeriod;
	
	public Ref_Class_mod(char freq, String geo, String unit, Double[] objective_timePeriod) {
		super();
		this.freq = freq;
		this.geo = geo;
		this.unit = unit;
		this.objective_timePeriod = objective_timePeriod;
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
	public Double[] getObjective_timePeriod() {
		return objective_timePeriod;
	}
	public void setObjective_timePeriod(Double[] objective_timePeriod) {
		this.objective_timePeriod = objective_timePeriod;
	}
	
	
}

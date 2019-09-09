package it.univipm.Gambini.Ragaini.Prog_OOP_CER.model;

public class Header{
	private String freq;
	private String geo;
	private String unit;
	private String objective_timePeriod;
	private Double[] years;
	
	public Header(String freq, String geo, String unit, String objective_timePeriod, Double[] years) {
		this.freq = freq;
		this.geo = geo;
		this.unit = unit;
		this.objective_timePeriod = objective_timePeriod;
		this.years = years;
	}
	
	public String getFreq() {
		return freq;
	}
	public void setFreq(String freq) {
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
	public void setObjective_timePeriod(String objective_timePeriod) {
		this.objective_timePeriod = objective_timePeriod;
	}
	public String getObjective_timePeriod() {
		return objective_timePeriod;
	}
	public Double[] getyears() {
		return years;
	}
	public void setyears(Double[] years) {
		this.years = years;
	}

}

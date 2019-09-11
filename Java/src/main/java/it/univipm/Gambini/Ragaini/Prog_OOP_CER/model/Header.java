package it.univipm.Gambini.Ragaini.Prog_OOP_CER.model;

public class Header{
	protected String freq;
	protected String geo;
	protected String unit;
	protected String objective_timePeriod;
	protected Integer[] years;
	
	public Header(String freq, String geo, String unit, String objective_timePeriod, Integer[] years) {
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
	public Integer[] getyears() {
		return years;
	}
	public void setyears(Integer[] years) {
		this.years = years;
	}
}

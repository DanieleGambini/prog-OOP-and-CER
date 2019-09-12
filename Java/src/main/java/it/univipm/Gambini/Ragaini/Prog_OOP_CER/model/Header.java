package it.univipm.Gambini.Ragaini.Prog_OOP_CER.model;

public class Header{
	protected String freq;
	protected String geo;
	protected String unit;
	protected String objective;
	protected Integer[] timePeriod;
	
	public Header(String freq, String geo, String unit, String objective, Integer[] timePeriod) {
		this.freq = freq;
		this.geo = geo;
		this.unit = unit;
		this.objective = objective;
		this.timePeriod = timePeriod;
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
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getObjective() {
		return objective;
	}
	public Integer[] gettimePeriod() {
		return timePeriod;
	}
	public void settimePeriod(Integer[] timePeriod) {
		this.timePeriod = timePeriod;
	}
}

package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;

import java.util.Enumeration;
import java.util.Vector;

public class Ref_Class_mod {
	private char freq;
	private String geo;
	private String unit;
	private String objective_timePeriod;
	private Double[] data_per_years;
	
	public Ref_Class_mod(char freq, String geo, String unit, String objective_timePeriod, Double[] data_per_years) {
		super();
		this.freq = freq;
		this.geo = geo;
		this.unit = unit;
		this.objective_timePeriod = objective_timePeriod;
		this.data_per_years = data_per_years;
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
	public void setObjective_timePeriod(String objective_timePeriod) {
		this.objective_timePeriod = objective_timePeriod;
	}
	public String getObjective_timePeriod() {
		return objective_timePeriod;
	}
	public Double[] getData_per_years() {
		return data_per_years;
	}
	public void setData_per_years(Double[] data_per_years) {
		this.data_per_years = data_per_years;
	}

	
	
	public static void printClass(Vector<Ref_Class_mod> vector) {
		Enumeration<Ref_Class_mod> element = vector.elements();
		System.out.println("\n Elements of vector:");
		while(element.hasMoreElements()) {
			System.out.println(element.nextElement() + "\n");
		}
		
		
	}
	
	
}

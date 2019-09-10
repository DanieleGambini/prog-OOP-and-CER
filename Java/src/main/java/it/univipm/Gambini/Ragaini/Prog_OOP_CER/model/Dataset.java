package it.univipm.Gambini.Ragaini.Prog_OOP_CER.model;

import java.util.Vector;

public class Dataset {
	
	protected Header header;
	protected Vector<Data> data;
	
	public Dataset(Header header, Vector<Data> data) {
		super();
		this.header = header;
		this.data = data;
	}
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Vector<Data> getData() {
		return data;
	}
	public void setData(Vector<Data> data) {
		this.data = data;
	}
	
/*
	@Override
	public String toString() {
		return "[Freq" + Header.getFreq() + "Geo" + Header.getGeo();
	}
*/
}

package it.univipm.Gambini.Ragaini.Prog_OOP_CER.service;

public interface RequestService {
	
	public abstract String dataRequest();
	public abstract String metadataRequest();
	public abstract String statsRequest();
	public abstract String rootRequest();
	public abstract String proofRequest();
	public abstract String connectRequest();
	public abstract String statsRequestFilter(String geo, String obj, String filterS);
	

}

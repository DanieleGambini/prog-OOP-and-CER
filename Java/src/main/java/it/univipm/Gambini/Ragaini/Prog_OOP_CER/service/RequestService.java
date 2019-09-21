package it.univipm.Gambini.Ragaini.Prog_OOP_CER.service;

public interface RequestService {
	
	public abstract String dataRequest(String filter);

	public abstract String dataRequest();
	
	public abstract String metadataRequest();
	
	public abstract String rootRequest();
	
	public abstract String proofRequest();
	
	public abstract String statsRequestFilter(String filter);

}

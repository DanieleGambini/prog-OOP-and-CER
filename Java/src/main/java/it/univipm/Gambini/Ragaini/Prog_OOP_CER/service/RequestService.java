package it.univipm.Gambini.Ragaini.Prog_OOP_CER.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

public interface RequestService {
	
	public abstract String dataRequest();
	public abstract String metadataRequest();
	public abstract String statsRequest();
	public abstract String rootRequest();
	public abstract String proofRequest();
	public abstract String connectRequest();
	public abstract String statsRequestFilter(String geo, String obj, String filterS) throws ProtocolException, MalformedURLException, IOException;
	

}

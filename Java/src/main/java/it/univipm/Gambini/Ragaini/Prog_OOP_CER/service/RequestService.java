package it.univipm.Gambini.Ragaini.Prog_OOP_CER.service;

//import java.util.Vector;

//import org.json.simple.JSONObject;

//import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Dataset;

//import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Dataset;

public interface RequestService {
	
	//JSONObject datasetRequest(Vector<Dataset> dataset);
	
	public abstract void dataRequest(/*Dataset dataset*/);
	public abstract void metadataRequest(/*Dataset dataset*/);
	public abstract void statsRequest(/*Dataset dataset*/);
	public abstract String datasetRequest(/*Dataset dataset*/);
	//public abstruct void ;

}

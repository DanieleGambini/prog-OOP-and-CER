package it.univipm.Gambini.Ragaini.Prog_OOP_CER.service;


//import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Dataset;


//import java.util.Vector;

//import org.json.simple.JSONObject;

//import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Dataset;

//import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Dataset;

public interface RequestService {
	
	//JSONObject datasetRequest(Vector<Dataset> dataset);
	
	public abstract String dataRequest(/*Dataset dataset*/);
	public abstract String metadataRequest(/*Dataset dataset*/);
	public abstract void statsRequest(/*Dataset dataset*/);
	

}

package it.univipm.Gambini.Ragaini.Prog_OOP_CER.service;

//import java.util.Vector;

//import org.json.simple.JSONObject;
//import org.json.simple.JSONValue;
//import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

//import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Dataset;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Parser;

@Service
public class ServiceImplementation implements RequestService{
	
	public ServiceImplementation() {
		System.out.println("ServiceImplementation");
		//Downloader.main(args);
		//Parser.main(args);
		System.out.println("End ServiceImplementation");
	}
	
	//@Override
	public String datasetRequest() {
		System.out.println("DataSetRequest function");
		String json_dataset = Parser.main(null).toString();
		/*JSONObject json_dataset = null;
		try {
			
			json_dataset = (JSONObject) JSONValue.parseWithException(Parser.main(null).toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("End DataSetRequest function");
		return json_dataset;
		
	}
	
	@Override
	public void metadataRequest() {
		System.out.println("End MetaDataRequest function");
		
		System.out.println("End MetaDataRequest function");
	}
	
	@Override
	public void statsRequest() {
		System.out.println("End StatsRequest function");
		
		System.out.println("End StatsRequest function");
	}
	
	@Override
	public void dataRequest() {
		System.out.println("End DataRequest function");
		
		System.out.println("End DataRequest function");
	}



}

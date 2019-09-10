package it.univipm.Gambini.Ragaini.Prog_OOP_CER.service;

//import java.util.Vector;

//import org.json.simple.JSONObject;
//import org.json.simple.JSONValue;
//import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Dataset;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Parser;

@Service
public class ServiceImplementation implements RequestService{
	
	private Dataset dataset;
	
	public ServiceImplementation() {
		System.out.println("ServiceImplementation");
		//Downloader.main(args);
		dataset = Parser.main(null);
		System.out.println("End ServiceImplementation");
	}
	@Override
	public String metadataRequest() {
		System.out.println("Start MetaDataRequest function");
		Gson gson = new Gson();
		String json = gson.toJson(dataset.getHeader());
		System.out.println(json);
			return json;
	}
	@Override
	public void statsRequest() {
		System.out.println("Start StatsRequest function");
		
		System.out.println("End StatsRequest function");
	}
	@Override
	public String dataRequest() {
		System.out.println("Start DataRequest function");
		Gson gson = new Gson();
		String json = gson.toJson(dataset.getData());
		System.out.println(json);
			return json;
		//System.out.println("End DataRequest function");
	}

	


}

package it.univipm.Gambini.Ragaini.Prog_OOP_CER.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Dataset;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Metadata;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch.Proof_Conversion;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Azure;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.ClassTo;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Downloader;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Filter;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Parser;

@Service
public class ServiceImplementation implements RequestService{

	private Dataset dataset;

	public ServiceImplementation() {
		//Downloader.main("http://data.europa.eu/euodp/data/api/3/action/package_show?id=vzo0vqtpcgMt3X8yBGTJ8Q");
		dataset = Parser.main(dataset,"dataset.csv");
		//String filter = "{ \"$not\": [ { \"GEO\": [\"IT\", \"LV\", \"SK\"] } , {\"OBJ\": [\"TOTAL\",] } ] }";
	}
	
	@Override
	public String metadataRequest() {
		return Metadata.MetadataGeneretor(dataset.getHeader());
	}
	
	@Override
	public String dataRequest() {
		return ClassTo.Json(dataset.getData());
	}
	
	@Override
	public String dataRequest(String filter) {
		if (filter.isEmpty()) {
			return ClassTo.Json(dataset.getData());
		}
		else {
			String subset = Filter.Controller(dataset, filter);
			return subset;
		}
		
	}
	
	
	@Override
	public String rootRequest() {
		StringBuilder data = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader("help.html"));
			String line = new String();
			while ((line = in.readLine()) != null ) {
				data.append(line);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data.toString();
	}
	
	
	@Override
	public String proofRequest() {
		return Proof_Conversion.Proof();
	}

	
	@Override
	public String statsRequestFilter(String geo, String obj, String filter) {
		return Azure.sendPost(geo, obj, filter, ClassTo.Json(dataset.getData()));
	}

	@Override
	public String statsRequestFilter(String filter) {
		String dataFiltered = Filter.Controller(dataset, filter);
		return Azure.sendPost(dataFiltered, filter);
	}
}

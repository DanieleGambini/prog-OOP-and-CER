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
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Parser;

@Service
public class ServiceImplementation implements RequestService{

	private Dataset dataset;

	public ServiceImplementation() {
		//Downloader.main(args);
		dataset = Parser.main(null);
	}
	
	
	@Override
	public String metadataRequest() {
		
		return Metadata.MetadataGeneretor(dataset.getHeader());
	}
	
	
	@Override
	public String statsRequest() {

		return "{" + "}";
	}
	
	
	@Override
	public String dataRequest() {
		
		return ClassTo.Json(dataset.getData());
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
	public String connectRequest() {
		
		return Azure.Connection();
	}


	@Override
	public String statsRequest(String geo, String obj) {
		
		return Azure.stats(geo, obj);
	}
	
	@Override
	public String statsRequestFilter(String geo, String obj) {
		
		return Azure.stats(geo, obj);
	}
}

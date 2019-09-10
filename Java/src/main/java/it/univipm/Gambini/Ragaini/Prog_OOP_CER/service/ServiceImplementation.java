package it.univipm.Gambini.Ragaini.Prog_OOP_CER.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	public String statsRequest() {
		System.out.println("Start StatsRequest function");
		
		System.out.println("End StatsRequest function");
		return "cappero";
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
	@Override
	public String rootRequest() {
		String line = null;
		try (BufferedReader buffer_reader = new BufferedReader(new FileReader("help.html"))) {
			System.out.println("rootRequest function");
			while ((line = buffer_reader.readLine()) != null ) {
			line = line + buffer_reader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	


}

package it.univipm.Gambini.Ragaini.Prog_OOP_CER.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Vector;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Dataset;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Header;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Metadata;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch.Metodi;
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
		Vector<String> appoggio = new Vector<>();
		Class<Header> h = Header.class;
		Field[] fields = h.getDeclaredFields();
		Gson gson = new Gson();
		String name = null;
		String attribute_type = null;
		int j = 0;
		for (Field e: fields) {
			name = fields[j].toString().substring(fields[j].toString().lastIndexOf(".")+1, fields[j].toString().length());
			System.out.println(name);
			Class<?> type = fields[j].getType();
			attribute_type = type.toString().substring(type.toString().lastIndexOf(".")+1, type.toString().length()).replace(";", "[]");
			String json = gson.toJson(new Metadata(name, attribute_type));
			appoggio.add(json);
			System.out.println(attribute_type);
			j++;
		}
		return appoggio.toString();
	}
	@Override
	public String statsRequest() {
		System.out.println("Start StatsRequest function");
		String metodi = Metodi.invoke_getter(dataset.getHeader());
		System.out.println("End StatsRequest function");
		return metodi;
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
		String line = "";
		String data = "";
		try (BufferedReader buffer_reader = new BufferedReader(new FileReader("help.html"))) {
			System.out.println("rootRequest function");
			while ((line = buffer_reader.readLine()) != null ) {
			data += buffer_reader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}

package it.univipm.Gambini.Ragaini.Prog_OOP_CER.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Data;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Dataset;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Metadata;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch.Proof_Conversion;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Cache;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.ClassTo;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Downloader;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Filter;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Parser;

/**
 * Classe che contiene l'implementazione dei metodi dell'interfaccia RequestService
 */
@Service
public class ServiceImplementation implements RequestService{

	private Dataset dataset;

	/**
	 * Metodo che attiva le procedure di downloading e di parsing presenti nelle classi: Downloader e Parser
	 */
	public ServiceImplementation() {
		Downloader.main("http://data.europa.eu/euodp/data/api/3/action/package_show?id=vzo0vqtpcgMt3X8yBGTJ8Q");
		dataset = Parser.main(dataset,"dataset.csv");
		System.out.println("READY");
	}
	
	/* Implementazione del metodo metadataRequest() chiamato nella classe RequestController */
	@Override
	public String metadataRequest() {
		Data data = new Data();
		return Metadata.MetadataGeneretor(dataset.getHeader(), data);
	}
	
	/* Implementazione del metodo dataRequest() chiamato nella classe RequestController */
	@Override
	public String dataRequest() {
		return ClassTo.Json(dataset.getData());
	}

	/* Implementazione del metodo dataRequest(String filter) chiamato nella classe RequestController, 
	 * Restituisce il sottoinsieme relativo al filtro immesso. 
	 */
	@Override
	public String dataRequest(String filter) {
		return Filter.Controller(dataset.getData(), filter);
	}
	
	/*Implementazione del metodo rootRequest chiamato nella classe RequestCeontroller.
	 * Carica da file, attraverso una struttura di buffer, un insieme di dati che restituisce come stringa.
	 * */
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
	
	
	/* 
	 * Implementazione del metodo proofRequest presente nella classe Scratch
	 */
	@Override
	public String proofRequest() {
		return Proof_Conversion.Proof();
	}

	
	/* 
	 * Implementazione del metodo statsRequestFilter presente nella classe RequestController.
	 */
	@Override
	public String statsRequestFilter(String filter) {
		return Cache.controller(dataset.getData(), filter); 

	}
}

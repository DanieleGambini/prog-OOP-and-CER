package it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Data;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Dataset;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Header;


public class Parser {
	
	final static String COMMA_DELIMITER = ",";
	final static String SEMICOLON_DELIMITER = ";";

	public static void main (String[] args) {
		
		// Dichiarazione Strutture dati
		Vector<Data> data_vector = new Vector<Data>();
		String line = new String();
		
		try (BufferedReader buffer_reader = new BufferedReader(new FileReader("dataset.csv"))) {
			line = buffer_reader.readLine();
			String[] semicolon_parse = line.split(SEMICOLON_DELIMITER);
			String tmp= semicolon_parse[semicolon_parse.length - 1];
			String tmp1 = tmp.substring(0,tmp.indexOf(','));
			semicolon_parse[semicolon_parse.length-1] = tmp1;
			String tmp2 = tmp.substring(tmp.indexOf(',') + 1);
			String[] comma_parse = tmp2.split(" ,");
			System.out.println(comma_parse);
			Double[] double_comma_parse = Arrays.stream(comma_parse).map(Double::valueOf).toArray(Double[]::new);
			System.out.println(double_comma_parse);
			Header header = new Header(semicolon_parse[0], semicolon_parse[1], semicolon_parse[2], semicolon_parse[3], double_comma_parse);
			
			while ((line = buffer_reader.readLine()) != null ) {
				semicolon_parse = line.split(SEMICOLON_DELIMITER);
				tmp= semicolon_parse[semicolon_parse.length - 1];
				tmp1 = tmp.substring(0,tmp.indexOf(','));
				semicolon_parse[semicolon_parse.length-1] = tmp1;
				tmp2 = tmp.substring(tmp.indexOf(',') + 1);
				comma_parse = tmp2.split(COMMA_DELIMITER);
				double_comma_parse = Arrays.stream(comma_parse).map(Double::valueOf).toArray(Double[]::new);
				data_vector.add(new Data(semicolon_parse[0].charAt(0), semicolon_parse[1], semicolon_parse[2], semicolon_parse[3], double_comma_parse));
			}
			
			Dataset dataset = new Dataset(header, data_vector);
			System.out.println(data_vector);
			System.out.println(dataset);
		}
		 catch (IOException i) {
				i.printStackTrace();
				return;
			}
		}

}

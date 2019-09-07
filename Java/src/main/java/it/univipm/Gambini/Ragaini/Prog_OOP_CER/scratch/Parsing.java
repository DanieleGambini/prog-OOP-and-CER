package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Parsing {

	final static String COMMA_DELIMITER = ",";
	final static String PERIOD_DELIMITER = ".";
	final static String SEMICOLON_DELIMITER = ";";
	final static String ENDLINE_DELIMITER = "\n";

	public static void main (String[] args) {
		
		// Dichiarazione Strutture dati
		List<List<String>> list = new ArrayList<>();
		Vector<Ref_Class_mod> vector = new Vector<Ref_Class_mod>();
		String first_line = new String();
		
		
		try (BufferedReader buffer_reader = new BufferedReader(new FileReader("dataset.csv"))) {
			first_line = buffer_reader.readLine();
			
			String[] intestazione = first_line.split(SEMICOLON_DELIMITER);
			String tmp= intestazione[intestazione.length-1];
			String tmp1 = tmp.substring(0,tmp.indexOf(','));
			intestazione[intestazione.length-1]=tmp1;
			String tmp2 = tmp.substring(tmp.indexOf(',')+1);
			String[] anni = tmp2.split(COMMA_DELIMITER);
			System.out.println(anni);
			
		
		}
		 catch (IOException i) {
				i.printStackTrace();
				return;
			}
		}

}

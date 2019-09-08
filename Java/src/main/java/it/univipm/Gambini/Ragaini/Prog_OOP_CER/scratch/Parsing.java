package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class Parsing {

	final static String COMMA_DELIMITER = ",";
	final static String PERIOD_DELIMITER = ".";
	final static String SEMICOLON_DELIMITER = ";";
	final static String ENDLINE_DELIMITER = "\n";

	public static void main (String[] args) {
		
		// Dichiarazione Strutture dati
		//List<List<String>> list = new ArrayList<>();
		Vector<Ref_Class_mod> vector = new Vector<Ref_Class_mod>();
		String line = new String();
		
		
		try (BufferedReader buffer_reader = new BufferedReader(new FileReader("dataset.csv"))) {
			line = buffer_reader.readLine();
			String[] intestazione = line.split(SEMICOLON_DELIMITER);
			String tmp= intestazione[intestazione.length - 1];
			String tmp1 = tmp.substring(0,tmp.indexOf(','));
			intestazione[intestazione.length-1] = tmp1;
			String tmp2 = tmp.substring(tmp.indexOf(',') + 1);
			String[] anni = tmp2.split(COMMA_DELIMITER);
			System.out.println(anni);
			Double[] double_anni = Arrays.stream(anni).map(Double::valueOf).toArray(Double[]::new);
			System.out.println(double_anni);
			
			Ref_Class_mod elem = new Ref_Class_mod(intestazione[0].charAt(0), intestazione[1], intestazione[2], intestazione[3], double_anni);
			vector.add(elem);
			
			System.out.println(elem);
			Ref_Class_mod.printClass(vector);
			/*
			while ((line = buffer_reader.readLine()) != null ) {
				String[] intestazione = line.split(SEMICOLON_DELIMITER);
				String tmp= intestazione[intestazione.length - 1];
				String tmp1 = tmp.substring(0,tmp.indexOf(','));
				intestazione[intestazione.length-1] = tmp1;
				String tmp2 = tmp.substring(tmp.indexOf(',') + 1);
				String[] anni = tmp2.split(COMMA_DELIMITER);
				System.out.println(anni);
				Double[] double_anni = Arrays.stream(anni).map(Double::valueOf).toArray(Double[]::new);
				System.out.println(double_anni);
				
				vector.add(new Ref_Class_mod(intestazione[0].charAt(0), intestazione[1], intestazione[2], double_anni));
				
				System.out.println(vector);
			}*/
			
			System.out.println(vector);
		}
		 catch (IOException i) {
				i.printStackTrace();
				return;
			}
		}

}

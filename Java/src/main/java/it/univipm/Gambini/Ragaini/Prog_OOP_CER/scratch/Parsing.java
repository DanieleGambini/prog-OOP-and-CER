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
			String[] semicolon_parse = line.split(SEMICOLON_DELIMITER);
			String tmp= semicolon_parse[semicolon_parse.length - 1];
			String tmp1 = tmp.substring(0,tmp.indexOf(','));
			semicolon_parse[semicolon_parse.length-1] = tmp1;
			String tmp2 = tmp.substring(tmp.indexOf(',') + 1);
			String[] comma_parse = tmp2.split(COMMA_DELIMITER);
			System.out.println(comma_parse);
			Double[] double_comma_parse = Arrays.stream(comma_parse).map(Double::valueOf).toArray(Double[]::new);
			System.out.println(double_comma_parse);
			
			Ref_Class_mod elem = new Ref_Class_mod(semicolon_parse[0].charAt(0), semicolon_parse[1], semicolon_parse[2], semicolon_parse[3], double_comma_parse);
			System.out.println(semicolon_parse[3]);
			System.out.println(elem.getObjective_timePeriod());
			vector.add(elem);
			
			System.out.println(elem);
			Ref_Class_mod.printClass(vector);
			
			while ((line = buffer_reader.readLine()) != null ) {
				semicolon_parse = line.split(SEMICOLON_DELIMITER);
				tmp= semicolon_parse[semicolon_parse.length - 1];
				tmp1 = tmp.substring(0,tmp.indexOf(','));
				semicolon_parse[semicolon_parse.length-1] = tmp1;
				tmp2 = tmp.substring(tmp.indexOf(',') + 1);
				comma_parse = tmp2.split(COMMA_DELIMITER);
				double_comma_parse = Arrays.stream(comma_parse).map(Double::valueOf).toArray(Double[]::new);
				vector.add(new Ref_Class_mod(semicolon_parse[0].charAt(0), semicolon_parse[1], semicolon_parse[2], semicolon_parse[3], double_comma_parse));
			}
			
			System.out.println(vector);
		}
		 catch (IOException i) {
				i.printStackTrace();
				return;
			}
		}

}

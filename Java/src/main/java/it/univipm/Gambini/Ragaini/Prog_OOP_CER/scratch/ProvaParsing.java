package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ProvaParsing {
	
	final static String COMMA_DELIMITER = ",";
	final static String PERIOD_DELIMITER = ".";
	final static String SEMICOLON_DELIMITER = ";";
	final static String ENDLINE_DELIMITER = "\n";
	
	public static void main (String[] args) {
		System.out.println("cavallo");
		ArrayList<String> array = new ArrayList<String>();
		array.add("capra");
		array.add("canguro");
		array.add("fagiano");
		array.add("cinghiale");
		array.add("orso");
		array.add("elefante");
		
		System.out.println(array);
		
		// Dichiarazione Strutture dati
		Object element = null; //cambiare Object con la classe 
		List<List<String>> list = new ArrayList<>();
		Vector<String> vector = new Vector<String>();
		
		// Apertura FileStream
		try {
			FileInputStream fileIn = new FileInputStream("dataset.csv");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			element = (Object) in.readObject();		//cambiare Object con la classe
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}
		/*
		// Lettura da FileStream
		try (BufferedReader buffer_reader = new BufferedReader(new FileReader("dataset.csv"))) {
			String line;
			while ((line = buffer_reader.readLine()) != null ) {
				String[] values = line.split(COMMA_DELIMITER);
				System.out.println(values.length);
				list.add(Arrays.asList(values));
				for (int i=0; i < values.length; i++) vector.add(new Object(values[i])); //da riempire
			}
			buffer_reader.close();
			
		} catch (IOException i) {
			i.printStackTrace();
			return;
		}
		*/
		//Print function
		for (Object item: vector) {
			System.out.println(vector.toString());
		}
		
		
	}

}

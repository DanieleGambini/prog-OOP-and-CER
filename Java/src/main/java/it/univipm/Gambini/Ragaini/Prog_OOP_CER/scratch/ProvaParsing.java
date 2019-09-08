package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class ProvaParsing {

	final static String COMMA_DELIMITER = ",";
	final static String PERIOD_DELIMITER = ".";
	final static String SEMICOLON_DELIMITER = ";";
	final static String ENDLINE_DELIMITER = "\n";

	public static void main (String[] args) throws FileNotFoundException, IOException {
		
		// Dichiarazione Strutture dati
		List<List<String>> list = new ArrayList<>();
		List<String> list1= new ArrayList<>();
		Vector<Ref_Class_mod> vector = new Vector<Ref_Class_mod>();
		String line = new String();
		
		/*
		// Apertura FileStream
		try {
			FileInputStream fileIn = new FileInputStream("dataset.csv");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			element = (Ref_Class_mod) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}*/
		/*
		// Lettura da FileStream
		try (BufferedReader buffer_reader = new BufferedReader(new FileReader("dataset.csv"))) {
			line = buffer_reader.readLine();
			list1.addAll(Arrays.asList(line.split(SEMICOLON_DELIMITER)));
			String t = list1.get(list1.size()-1).toString();
			list1.remove(list1.size()-1);
			list1.addAll(Arrays.asList(t.split(COMMA_DELIMITER)));
			
		}
			
			while ((line = buffer_reader.readLine()) != null ) {
				String[] values = line.split(SEMICOLON_DELIMITER);
				values += line.split(COMMA_DELIMITER);
				System.out.println(values.length);
				list.add(Arrays.asList(values));
				Double[] a = { Double.parseDouble(values[4]), Double.parseDouble(values[5]), Double.parseDouble(values[6]), Double.parseDouble(values[7]), Double.parseDouble(values[8]), Double.parseDouble(values[9]), Double.parseDouble(values[10]), Double.parseDouble(values[11]), Double.parseDouble(values[12]), Double.parseDouble(values[13]), Double.parseDouble(values[14]), Double.parseDouble(values[15]), Double.parseDouble(values[16]), Double.parseDouble(values[17]), Double.parseDouble(values[18]), Double.parseDouble(values[19]), Double.parseDouble(values[20]), Double.parseDouble(values[21]), };
				vector.add(new Ref_Class_mod(values[0].charAt(0),values[1], values[2], a)); //da riempire
			}
			buffer_reader.close();
*/
		//Print function
		for (Object item: vector) {
			System.out.println(item);
			System.out.println(vector.toString());
		}

		List<List<String>> records2 = new ArrayList<>();
		try (Scanner s = new Scanner(new File("dataset.csv"));) {
			while (s.hasNextLine()) {
				records2.add(getRecordFromLine(s.nextLine()));
			}
			s.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		}
	}

	private static List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return values;
	}


}
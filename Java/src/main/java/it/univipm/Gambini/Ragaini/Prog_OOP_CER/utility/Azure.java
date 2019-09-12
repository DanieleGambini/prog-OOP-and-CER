package it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Azure {

	public static String Connection( ) {
		URL url = null;
		try {
			url = new URL("http://127.0.0.1:5000/start");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		StringBuilder response = new StringBuilder();
		try {
			Scanner s = new Scanner(url.openStream());
			while (s.hasNext()) {
				response.append(s.nextLine());
			}
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Connected to Azure");
		return response.toString();
	}
	
	public static String average(int start_year, int end_year) {
		
		System.out.println("prova query: " + start_year + " " + end_year);
		String str = Integer.toString(start_year) + " " + Integer.toString(end_year);
		return str;
	}
}

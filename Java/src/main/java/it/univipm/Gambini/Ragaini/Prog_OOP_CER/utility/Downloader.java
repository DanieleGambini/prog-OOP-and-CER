package it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 * Classe contente la procedura di dowloading del dataset di riferimento 
 *
 */
public class Downloader {

	/**
	 * Procedura che effettua il downloading del dataset
	 * @param arg url di riferimento
	 */
	public static void main(String arg) {

		try {

			URLConnection openConnection = new URL(arg).openConnection();
			openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			/* Crea il processo di lettura del file.
			 * in é il collegamento che descrive la lettura del file.
			 */
			InputStream in = openConnection.getInputStream();

			 String data = "";
			 String line = "";
			 try {
				 /* Crea un InputStreamReader che non puó leggere piú di un carattere alla volta.
				  * Tutto ció che viene letto dal lettore viene messo in un buffer.
				  */
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );

			   /* fin quando il buffer non é vuoto, allora continua ad assegnare il contenuto a line.
			    * Il ciclo smetterá quando line contiene tutto ció che é contenuto in buf.
			    */
			   while ( ( line = buf.readLine() ) != null ) {
				   data+= line;
				   System.out.println( line );
			   }
			 } finally {
			   in.close();
			 }
			 /* obj é l’oggetto scaricato dall’url, ovvero tutto quello che é contenuto dalle parentesi graffe principali.
			  * obj é l’array di oggetti di tipo JSONObject che compone il .json.
			  * Per ogni oggetto presente dentro l’oggetto principale, crea un elemento dell’array come un’elemento di una HashMap a cui
			  * é associata una chiave e un valore.
			  * La chiave é il nome dell’attributo, mentre il valore é il valore dell’attributo della chiave corrispondente del JSONObject.
			  * La procedura continua ricorsivamente fino ad incorporare ogni elemento del .json.
			 */
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data);
			/* objI é l’oggetto "result" presente nell’oggetto obj.
			 * Contiene tutto ció che é contenuto nel campo "result" del .json.
			*/
			JSONObject objI = (JSONObject) (obj.get("result"));
			/* É un’ array di JSONObject ed ognuno contiene istanza di "resources" all’interni di "result".
			 *
			 */
			JSONArray objA = (JSONArray) (objI.get("resources"));

			/* Per ogni istanza di Object presente in objA, verifica se é a sua volta una istanza di JSONObject.
			 * In caso affermativo, assegno l’oggetto a o1 e cerco in esso i campi che corrispondono a "format" e "url".
			 * Se all’interno della variabile format trovo *.csv allora eseguo il download.
			 * Utilizzo l’istanza dell’oggetto o1 perché o é l’indice dell’array.
			 */
			for(Object o: objA){
			    if ( o instanceof JSONObject ) {
			        JSONObject o1 = (JSONObject)o;
			        String format = (String)o1.get("format");
			        String urlD = (String)o1.get("url");
			        System.out.println(format + " | " + urlD);
			        if(format.contains("CSV")) {
			        	download(urlD, "dataset.csv");
			        }
			    }
			}
			System.out.println( "OK" );

			// Exceptions
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Procedura che apre un percorso url e ne stampa su file il contenuto 
	 */
	public static void download(String url, String fileName) throws Exception {
	    try (InputStream input = URI.create(url).toURL().openStream()) {
	    	System.out.println("Printing file path");
	        Files.copy(input, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
	    	System.out.println(Paths.get(fileName));
	    	input.close();
	    }
	}

}

package it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Data;

/**
 * Classe che gestisce la cache per le statistiche.
 */
public class Cache {

	static String relpath = "cacheFiles" + File.separator;
	static String ext = ".json";
	static String cachePath = relpath+"cache"+ext;
	
	
	/**
	 * Questo metodo sulla base delle richieste del client restituisce le statistiche che sono giá state richieste,
	 * altrimenti inoltra la richiesta ad Azure e restituisce di conseguenza.
	 * @param data vettore contente tutte le istanze della classe Data ricavate dal dataset di riferimento
	 * @param filter filterRequest stringa di oggetti formato json contenente i filtri inoltrati dall'utente
	 * { "$": [ {"GEO": ["", "", ... , "" }, { "OBJ": ["", "", ... , ""] } ], "$start": "", "$end": "" }
	 * @return string contenente le statistiche del dataset richiesto
	 */
	public static String controller(Vector<Data> data, String filter) {
		
		JSONObject cache = cacheOpen();
		
		if (filter.isEmpty()) {
			return Cache.FileOpen(relpath+"datasetStats"+ext);
		}
		String key = Integer.toString(keyGenerator(filter));
		if (cache.containsKey(key)) {
			return FileOpen(relpath+key+ext);
		}
		
		else {
			String dataFiltered = Filter.Controller(data, filter);
			String stats = Azure.sendPost(dataFiltered, filter);
			newFileCache(cache, stats, key);
			return stats;
		}
	}


	/** Metodo ausiliario che apre un file indicandone path e nome.
	 * @param fileName path del file system e nome del file.
	 * @return stringa contenente l’oggetto del file.
	 */
	private static String FileOpen(String fileName) {
		String pathFile = fileName;
		File file = new File(pathFile);
		StringBuilder data = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
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
		String f = data.toString();
		return f;
	}

	/** Metodo ausiliario che restituisce il contenuto del file cache e ne restituisce il contenuto in formato json.
	 * @return oggetto formato json.
	 */
	private static JSONObject cacheOpen() {
		File file = new File(relpath+"cache"+ext);
		String line = new String();
		try (BufferedReader buffer_reader = new BufferedReader(new FileReader(file))) {
			line = buffer_reader.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject cache = new JSONObject();
		try {
			cache = (JSONObject) JSONValue.parseWithException(line);
		} catch (ParseException ee) {
			ee.printStackTrace();
		}
		
		return cache;
	}

	/** Metodo ausiliario che scrive su file il conenuto che gli viene fornito in un file.
	 * @param data stringa oggetto che si vuole scrivere in memoria di massa.
	 * @param fileName stringa che specifica il percorso e il nome del file.
	 */
	private static void writeOnFile(String data, String fileName) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"))) {
			writer.write(data);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Metodo ausiliario che crea un nuovo file e che viene inserito nella tabella degli elementi presenti nella cache.
	 * @param cache oggetto json degli elementi presenti.
	 * @param stats contenuto json che deve essere scritto su file.
	 * @param key stringa che funge da chiave per la cache.
	 */
	@SuppressWarnings("unchecked")
	private static void newFileCache(JSONObject cache, String stats, String key) {
		String fileName = relpath + key + ext;
		cache.putIfAbsent(key, fileName);
		writeOnFile(cache.toJSONString(), relpath+"cache"+ext);
		writeOnFile(stats, fileName);
		}
	
	/** Metodo ausiliario che restituisce l’operazione logica presente nel filtro.
	 * @param operation oggetto json che é presente nel filtro.
	 * @return stringa dell’operazione logica corrispondente.
	 */
	private static String opSelector(JSONObject operation) {
		
		if (operation.containsKey("$in")) {
			return "$in";
		}
		else if (operation.containsKey("$not")) {
			return "$not";
		}
		else if (operation.containsKey("$and")) {
			return "$and";
		}
		else {
			throw new IllegalArgumentException("Error: operation insered not valid");
		}
	}
	
	/** Metodo ausiliario che genera una chiave identificativa.
	 * @param filter stringa del filtro inoltrato.
	 * @return intero che rappresenta la chiave generata.
	 */
	private static int keyGenerator(String filter) {
		JSONObject operation = new JSONObject();
		try {
			operation = (JSONObject) JSONValue.parseWithException(filter);
		} catch (ParseException ee) {
			ee.printStackTrace();
		}
		
		int a = 0, b = 0;
		
		if (operation.containsKey("$start")) {
			a = Integer.parseInt((String) operation.get("$start"));
		}
		if (operation.containsKey("$end")) {
			b = Integer.parseInt((String) operation.get("$end"));
		}
		int c = 0, d = 0,e = 0;
		
		String op = opSelector(operation);
		
		List<List<String>> values = Filter.initializer(operation, op);
		List<String> geo = values.get(0);
		List<String> obj = values.get(1);
		
		for (int k=0; k<op.length(); k++) {
			char charop = op.charAt(k);
			e += charop;
		}
		
		for (String i: geo) {
			for(int j=0; j<i.length(); j++) {
				char cgeo = i.charAt(j);
				c += (int) cgeo;
			}
		}
		
		for (String i: obj) {
			for(int j=0; j<i.length(); j++) {
				char cobj = i.charAt(j);
				d += (int) cobj;
			}
		}
		
		int key = ((a*b*e)+(c*d));
		return key;
		
	}
	
}


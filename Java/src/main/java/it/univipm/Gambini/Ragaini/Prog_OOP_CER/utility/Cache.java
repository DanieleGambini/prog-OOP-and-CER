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
 * Classe che gestisce i la cache per 
 */
public class Cache {

	static String relpath = "cacheFiles" + File.separator;
	static String ext = ".json";
	static String cachePath = relpath+"cache"+ext;
	
	
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
	
	@SuppressWarnings("unchecked")
	private static void newFileCache(JSONObject cache, String stats, String key) {
		String fileName = relpath + key + ext;
		cache.putIfAbsent(key, fileName);
		writeOnFile(cache.toJSONString(), relpath+"cache"+ext);
		writeOnFile(stats, fileName);
		}
	
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


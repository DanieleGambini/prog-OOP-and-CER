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

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class Cache {

	public String controller(String stats, String filter) {

		String relpath = "";
		if (filter == "") {
			return FileOpen("wholestats.json");
		}

		JSONObject cache = cacheOpen();
		
		String key = Integer.toString(keyGenerator(filter));
		
		if (cache.containsKey(key)) {
			return FileOpen(key);
		}
		
		else {
			newFileCache(stats, key+".json");
			return stats;
		}
	}


	private String FileOpen(String fileName) {
		
		File file = new File(fileName);
		String line = new String();
		try (BufferedReader buffer_reader = new BufferedReader(new FileReader(file))) {
			line = buffer_reader.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	private JSONObject cacheOpen() {
		File file = new File("cacheFiles/cache.json");
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

	private void newFileCache(String stats, String fileName) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"))) {
			writer.write(stats);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private String opSelector(JSONObject operation) {
		
		if (operation.containsKey("$in")) {
			return "$in";
		}
		else if (operation.containsKey("$not")) {
			return "$not";
		}
		else {
			return "$and";
		}
	}
	
	private String yearSelector(JSONObject filter) {
		if (filter.containsKey("$start")) {
			return (String) filter.get("$start");
		}
		else if (filter.containsKey("$end")) {
			return (String) filter.get("$end");
		}
		else return "";
		
	}
	
	private int keyGenerator(String filter) {
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
		
		int key = (a+b+c+d)*e;
		return key;
		
	}
	
}


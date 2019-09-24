package it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Data;

/**
 * Classe che gestisce i filtri sul dataset di riferimento in base alle richieste dell'utente.
 *
 */
public class Filter {
	

	final static String COMMA_DELIMITER = ",";

	/**Metodo che, sulla base dei parametri forniti dall'utente, gestisce le richieste relative ai filtri sul dataset 
	 * @param dataset vettore contente tutte le istanze della classe Data ricavate dal dataset di riferimento
	 * @param filterRequest stringa di oggetti formato json contenente i filtri inoltrati dall'utente
	 * { "$": [ {"GEO": ["", "", ... , "" }, { "OBJ": ["", "", ... , ""] } ], "$start": "", "$end": "" }
	 * @return array di oggetti in formato json (sotto forma stringa) sottoinsieme del dataset di riferimento 
	 */
	public static String Controller(Vector<Data> dataset, String filterRequest) {
		JSONObject filter = new JSONObject();
		try {
			filter = (JSONObject) JSONValue.parseWithException(filterRequest);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Vector<Data> subset = dataset;
		if (filter.containsKey("$in") || filter.containsKey("$or")) {
			List<List<String>> initialized = initializer(filter, "$in");			
			subset = IN_OR(subset, initialized.get(0) , initialized.get(1));
		}

		else if (filter.containsKey("$not") || filter.containsKey("$nin")) {
			List<List<String>> initialized = initializer(filter, "$not");
			subset = NIN_NOT(subset, initialized.get(0) , initialized.get(1));
		}

		else if (filter.containsKey("$and")) {
			List<List<String>> initialized = initializer(filter, "$and");
			subset = AND(subset, initialized.get(0) , initialized.get(1));
		} else {
			throw new IllegalArgumentException("Error: operation insered not valid");
			}

		if (filter.containsKey("$start") || filter.containsKey("$end")) {
			if ( filter.containsKey("$start")) {
				if ( Integer.parseUnsignedInt((String) filter.get("$start"))>1999 || Integer.parseUnsignedInt((String) filter.get("$start"))<2018) {
					subset = startYearSelector(subset, Integer.parseUnsignedInt((String) filter.get("$start"))-2000);
				} else {
					throw new IllegalArgumentException("Error: start year insered not valid");
				}
			} if ( filter.containsKey("$end")) {
				if( Integer.parseUnsignedInt((String) filter.get("$end"))>1999 || Integer.parseUnsignedInt((String) filter.get("$end"))<2018) {
					subset = endYearSelector(subset, 2017-Integer.parseUnsignedInt((String) filter.get("$end")));
				} else {
					throw new IllegalArgumentException("Error: end year insered not valid");
				}
			}
		}
		return ClassTo.Json(subset);
	}

	/** Metodo ausiliario che seleziona dal dataset di riferimento le istanze richieste a partire dal parametro numerico specificato
	 * @param dataset vettore contente tutte le istanze della classe Data ricavate dal dataset di riferimento
	 * @param start intero che funge da indice per la selezione
	 * @return vettore di istanze della classe Data sottoinsieme del dataset di riferimento
	 */
	@SuppressWarnings("unused")
	private static Vector<Data> startYearSelector(Vector<Data> dataset, int start) {
		Vector<Data> result = new Vector<>();
		for (int j=0; j<dataset.size(); j++) {
			Data row = dataset.get(j);
			List<Double> subyears = new ArrayList<>();
			Collections.addAll(subyears, row.gettimePeriod());
			for (int i=0; i<start; i++) {
				subyears.remove(0);
			}
			Double[] trim = new Double[subyears.size()];
			for ( int i=0; i<subyears.size(); i++) {
				trim[i]=subyears.get(i);
			}
			result.add(new Data(row.getFreq(),row.getGeo(),row.getUnit(),row.getObjective(), trim)); 
		}
		return result;
	}

	/**
	 * Metodo ausiliario che seleziona dal dataset di riferimento le istanze richieste entro il parametro numerico specificato
	 * @param dataset vettore contente tutte le istanze della classe Data ricavate dal dataset di riferimento
	 * @param start intero che funge da indice per la selezione
	 * @return vettore di istanze della classe Data sottoinsieme del dataset di riferimento
	 */
	@SuppressWarnings("unused")
	private static Vector<Data> endYearSelector(Vector<Data> dataset, int end) {
		Vector<Data> result = new Vector<>();
		for (int j=0; j<dataset.size(); j++) {
			Data row = dataset.get(j);
			List<Double> subyears = new ArrayList<>();
			Collections.addAll(subyears, row.gettimePeriod());
			for (int i=0; i<end; i++) {
				subyears.remove(subyears.size()-1);
			}
			Double[] trim = new Double[subyears.size()];
			for ( int i=0; i<subyears.size(); i++) {
				trim[i]=subyears.get(i);
			}
			result.add(new Data(row.getFreq(),row.getGeo(),row.getUnit(),row.getObjective(), trim)); 
		}
		return result;
	}
	
	/** Metodo ausiliario che decodifica un oggetto di tipo json in una stuttura dati utilizzabile dal programma 
	 * @param filter oggetto di tipo json
	 * @param op stringa di caratteri
	 * @return lista composta da due elementi di tipo di lista
	 */
	public static List<List<String>> initializer(JSONObject filter, String op) {
		List<List<String>> result = new ArrayList<>();
		JSONArray filterOP = (JSONArray) filter.get(op);
		JSONObject p1 = (JSONObject) filterOP.get(0);
		if (p1.containsKey("GEO")) {
			JSONArray geo = (JSONArray) p1.get("GEO");
			List<String> geoValues = new ArrayList<String>();
			for (int i=0; i<geo.size(); i++) {
				geoValues.add(geo.get(i).toString());
			}
			result.add(geoValues);
		}
			JSONObject p2 = (JSONObject) filterOP.get(filterOP.size()-1);
			if (p2.containsKey("OBJ")) {
				JSONArray obj = (JSONArray) p2.get("OBJ");
				List<String> objValues = new ArrayList<String>();
				for (int i=0; i<obj.size(); i++) {			
					objValues.add(obj.get(i).toString());
				}
				result.add(objValues);
			}
			
		if (result.size()==1) {
			result.add(result.get(0));
		}
		return result;
	}
	
	
	

	/**Metodo ausiliario che svolge le funzioni: NIN (nel caso di parametri multipli), NOT (nel caso di parametri singoli) 
	 * @param dataset vettore contente tutte le istanze della classe Data ricavate dal dataset di riferimento
	 * @param geoValues lista di elementi di tipo stringa che rappresenta le informazioni prelevate dal json
	 * @param objValues lista di elementi di tipo stringa che rappresenta le informazioni prelevate dal json
	 * @return vettore di istanze della classe Data sottoinsieme della classe di riferimento
	 */
	private static Vector<Data> NIN_NOT(Vector<Data> dataset, List<String> geoValues, List<String> objValues) {
		for (String geoValue: geoValues) {
			for (int j=0; j<dataset.size(); j++) {
				@SuppressWarnings("unused")
				Data row = dataset.get(j);
				if (row.getGeo().contentEquals(geoValue)) {
					dataset.remove(j);
				}
			}
		}
		for (String objValue: objValues) {
			for (int j=0; j<dataset.size(); j++) {
				@SuppressWarnings("unused")
				Data row = dataset.get(j);
				if (row.getObjective().contentEquals(objValue)) {
					dataset.remove(j);
				}
			}
		}
		return dataset;
	}

	/**Metodo ausiliario che svolge le funzioni: IN (nel caso di parametri multipli), OR (nel caso di parametri singoli) 
	 * @param dataset vettore contente tutte le istanze della classe Data ricavate dal dataset di riferimento
	 * @param geoValues lista di elementi di tipo stringa che rappresenta le informazioni prelevate dal json
	 * @param objValues lista di elementi di tipo stringa che rappresenta le informazioni prelevate dal json
	 * @return vettore di istanze della classe Data sottoinsieme della classe di riferimento
	 */
	private static Vector<Data> IN_OR(Vector<Data> dataset, List<String> Gvalues, List<String> Ovalues) {
		Vector<Data> result = new Vector<>();
		for (String value: Gvalues) {
			for(Data row: dataset) {
				if (row.getGeo().contentEquals(value)) {
					result.add(row);
				}
			}
		}
		for (String value: Ovalues) {
			for(Data row: dataset) {
				if (row.getObjective().contentEquals(value)) {
					result.add(row);
				}
			}
		}
		return result;
	}

	/**Metodo ausiliario che svolge la funzione AND 
	 * @param dataset vettore contente tutte le istanze della classe Data ricavate dal dataset di riferimento
	 * @param geoValues lista di elementi di tipo stringa che rappresenta le informazioni prelevate dal json
	 * @param objValues lista di elementi di tipo stringa che rappresenta le informazioni prelevate dal json
	 * @return vettore di istanze della classe Data sottoinsieme della classe di riferimento
	 */
	private static Vector<Data> AND(Vector<Data> dataset, List<String> geoValues, List<String> objValues) {
		Vector<Data> result = new Vector<>();
		Vector<Data> tmp = new Vector<>();
		for (String value: geoValues) {
			for(Data row: dataset) {
				if (row.getGeo().contentEquals(value)) {
					tmp.add(row);
				}
			}
		}
		for(String value: objValues) {
			for(Data row: tmp) {
				if (row.getObjective().contentEquals(value)) {
					result.add(row);
				}
			}
		}
		return result;
	}
}
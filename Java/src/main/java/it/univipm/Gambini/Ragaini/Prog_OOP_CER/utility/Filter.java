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
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Dataset;

public class Filter {

	final static String COMMA_DELIMITER = ",";

	public static String Controller(Dataset dataset, String filterRequest) {		
		JSONObject filter = new JSONObject();
		try {
			filter = (JSONObject) JSONValue.parseWithException(filterRequest);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Vector<Data> subset = operationSelector(dataset.getData(), filter);
		return ClassTo.Json(subset);
	}

	private static Vector<Data> operationSelector(Vector<Data> dataset, JSONObject filter) {
		Vector<Data> subset = dataset;
		if (filter.containsKey("$in") || filter.containsKey("$or")) {
			List<List<String>> initialized = initializer(filter, "$in");
			subset = IN_OR(subset, initialized.get(0) , initialized.get(1));
		}

		if (filter.containsKey("$not")) {
			List<List<String>> initialized = initializer(filter, "$not");
			subset = NOT(subset, initialized.get(0) , initialized.get(1));
		}

		if (filter.containsKey("$and")) {
			List<List<String>> initialized = initializer(filter, "$and");
			subset = AND(subset, initialized.get(0) , initialized.get(1));
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
					throw new IllegalArgumentException("Error: start year insered not valid");
				}
			}
		} else {
			throw new IllegalArgumentException("Error: years insered not valid");
			}
		return subset;
	}

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

	private static List<List<String>> initializer(JSONObject filter, String op) {
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
		JSONObject p2 = (JSONObject) filterOP.get(1);
		if (p2.containsKey("OBJ")) {
			JSONArray obj = (JSONArray) p2.get("OBJ");
			List<String> objValues = new ArrayList<String>();
			for (int i=0; i<obj.size(); i++) {			
				objValues.add(obj.get(i).toString());
			}
			result.add(objValues);
		}
		return result;
	}

	private static Vector<Data> NOT(Vector<Data> dataset, List<String> geoValues, List<String> objValues) {
		for (String geoValue: geoValues) {
			for (int j=0; j<dataset.size(); j++) {
				@SuppressWarnings("unused")
				Data row = dataset.get(j);
				if (row.getGeo().equals(geoValue)) {
					dataset.remove(j);
				}
			}
		}
		for (String objValue: objValues) {
			for (int j=0; j<dataset.size(); j++) {
				@SuppressWarnings("unused")
				Data row = dataset.get(j);
				if (row.getObjective().equals(objValue)) {
					dataset.remove(j);
				}
			}
		}
		return dataset;
	}

	private static Vector<Data> IN_OR(Vector<Data> dataset, List<String> Gvalues, List<String> Ovalues) {
		Vector<Data> result = new Vector<>();
		for (String value: Gvalues) {
			for(Data row: dataset) {
				if (row.getGeo().equals(value)) {
					result.add(row);
				}
			}
		}
		for (String value: Ovalues) {
			for(Data row: dataset) {
				if (row.getObjective().equals(value)) {
					result.add(row);
				}
			}
		}
		return result;
	}

	private static Vector<Data> AND(Vector<Data> dataset, List<String> geoValues, List<String> objValues) {
		Vector<Data> result = new Vector<>();
		Vector<Data> tmp = new Vector<>();
		for (String value: geoValues) {
			for(Data row: dataset) {
				if (row.getGeo().equals(value)) {
					tmp.add(row);
				}
			}
		}
		for(String value: objValues) {
			for(Data row: tmp) {
				if (row.getObjective().equals(value)) {
					result.add(row);
				}
			}
		}
		return result;
	}
}
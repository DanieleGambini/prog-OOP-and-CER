package it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility;

import com.google.gson.Gson;

public class ClassTo {
	
	public static String Json(Object clas) {
		Gson gson = new Gson();
		String json = gson.toJson(clas);
		//System.out.println(json);
		return json;
	}
	

}

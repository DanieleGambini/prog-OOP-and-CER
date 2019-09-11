package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;

import java.lang.reflect.Field;
import java.util.Vector;

import com.google.gson.Gson;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Metadata;

public class ClassToJsonConversion {
	
	public static void  CtJc() {
		Vector<String> appoggio = new Vector<>();
		Class<Ref_Class> h = Ref_Class.class;
		Field[] fields = h.getDeclaredFields();
		Gson gson = new Gson();
		String name = null;
		String attribute_type = null;
		int j = 0;
		for (Field e: fields) {
			name = fields[j].toString();//.substring(fields[j].toString().lastIndexOf(".")+1, fields[j].toString().length());
			System.out.println(name);
			Class<?> type = fields[j].getType();
			attribute_type = type.toString();//.substring(type.toString().lastIndexOf(".")+1, type.toString().length()).replace(";", "[]");
			String json = gson.toJson(new Metadata(name, attribute_type));
			appoggio.add(json);
			System.out.println(attribute_type);
			j++;
		}
		System.out.println(appoggio.toString());
	}
	
}
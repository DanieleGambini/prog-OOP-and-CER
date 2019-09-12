package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;

import java.lang.reflect.Field;
import java.util.Vector;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Metadata;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.ClassTo;

public class Proof_Conversion {
	
	public static String  Proof() {
		Vector<String> appoggio = new Vector<>();
		Class<Ref_Class> h = Ref_Class.class;
		Field[] fields = h.getDeclaredFields();
		String name = null;
		String attribute_type = null;
		int j = 0;
		for (@SuppressWarnings("unused") Field e: fields) {
			name = fields[j].toString();//.substring(fields[j].toString().lastIndexOf(".")+1, fields[j].toString().length());
			System.out.println(name);
			Class<?> type = fields[j].getType();
			attribute_type = type.toString();//.substring(type.toString().lastIndexOf(".")+1, type.toString().length()).replace(";", "[]");
			appoggio.add(ClassTo.Json(new Metadata(name, " ", attribute_type)));
			System.out.println(attribute_type);
			j++;
		}
		//System.out.println(appoggio.toString());
		return appoggio.toString();
	}
	
}
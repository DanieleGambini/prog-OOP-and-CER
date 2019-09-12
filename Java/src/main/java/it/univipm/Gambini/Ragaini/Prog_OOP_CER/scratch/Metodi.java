package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Header;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Metadata;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.ClassTo;

public class Metodi {
	
	
	
	public static Vector<String> invoke_Name_and_Type(Header header) {
		
		Vector<String> appoggio = new Vector<>();
		Class<Header> h = Header.class;
		Field[] fields = h.getDeclaredFields();
		
		ArrayList<?> sf = Metodi.invoke_getter(header);
		
		String name = new String();
		String attribute_type = new String();
		String json = new String();
		
		int j = 0;

		for (@SuppressWarnings("unused") Field e: fields) {
			name = fields[j].toString().substring(fields[j].toString().lastIndexOf(".")+1, fields[j].toString().length());
			
			Class<?> type = fields[j].getType();
			attribute_type = type.toString().substring(type.toString().lastIndexOf(".")+1, type.toString().length()).replace(";", "[]");
			
			json = ClassTo.Json(new Metadata(name, sf.toArray()[j].toString(), attribute_type));
			
			appoggio.add(json);
			
			j++;
		}
		
		return appoggio;
		
	}
	
	
	
	public static ArrayList<String> invoke_getter(Header header) {

		ArrayList<String> getterResults = new ArrayList<>();
		try {
			Class<?> headerClass = Class.forName("it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Header");
			Method[] methods = headerClass.getDeclaredMethods();

			for (Method method : methods) {
				if (method.getName().startsWith("get")){
					if(method.getReturnType().isArray()) { 
						String appoggio = Arrays.deepToString((Object[]) method.invoke(header));
						getterResults.add(appoggio);
					}
					else {
						getterResults.add(method.invoke(header).toString());
					}

				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return getterResults;
	}
}
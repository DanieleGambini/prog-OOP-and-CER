package it.univipm.Gambini.Ragaini.Prog_OOP_CER.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.ClassTo;

public class Metadata {

	protected String alias;
	protected String sourceField;
	protected String type;

	public Metadata(String alias, String sourceField, String type) {
		super();
		this.alias = alias;
		this.sourceField = sourceField;
		this.type = type;
	}

	public static String MetadataGeneretor(Header header, Data data) {

		/* SourceField Dynamic Generator
		 * "descrizione"
		 */
	
		ArrayList<String> getterResults = new ArrayList<>();
		try {
			Class<?> headerClass = Class.forName("it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Header");
			Method[] methods = headerClass.getDeclaredMethods();

			for (Method method : methods) {
				if (method.getName().startsWith("get")){
					if(method.getReturnType().isArray()) { 
						String tmp = Arrays.deepToString((Object[]) method.invoke(header));
						getterResults.add(tmp);
					}
					else {
						getterResults.add(method.invoke(header).toString());
					}

				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 

		
		/* Alias and Type Dynamic Generator
		 * "descrizione"
		 */
		Vector<String> tmp = new Vector<>();
		Class<Data> h = Data.class;
		Field[] fields = h.getDeclaredFields();
		String field_type = new String();
		String json = new String();
		String name = new String();
		int j = 0;
		
		for (@SuppressWarnings("unused") Field e: fields) {
			// get aliases
			name = fields[j].toString().substring(fields[j].toString().lastIndexOf(".")+1, fields[j].toString().length());

			// get types
			Class<?> types = fields[j].getType();	// Possibilmente togliere la dichiarazione da dentro il for
			field_type = types.toString().substring(types.toString().lastIndexOf(".")+1, types.toString().length()).replace(";", "[]");

			json = ClassTo.Json(new Metadata(name, getterResults.toArray()[j].toString(), field_type));
			tmp.add(json);
			j++;
		}

		// returns the Vector<String> containing the json objects casted  to string;
		return tmp.toString();
	}
}
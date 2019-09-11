package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Header;

public class Metodi {
		public static String invoke_getter(Header header) {
			
			ArrayList<String> getterResults = new ArrayList<>();
			try {
				Class headerClass = Class.forName("it.univipm.Gambini.Ragaini.Prog_OOP_CER.model.Header");
				Method[] methods = headerClass.getDeclaredMethods();
				
				for (Method method : methods) {
					if (method.getName().startsWith("get")&&!method.getReturnType().isArray()) {
						getterResults.add(method.invoke(header).toString());	
						}
					}
				
				
			//Log.d(getterResults.toString());
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
			return getterResults.toString();
		}
}
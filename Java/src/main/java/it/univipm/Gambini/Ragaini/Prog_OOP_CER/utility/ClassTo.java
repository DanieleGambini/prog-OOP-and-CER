package it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility;

import com.google.gson.Gson;

/**
 * Classe contenente il metodo che effettua la conversione in stringa (formato json) di un oggetto di generica classe
 *
 */
public class ClassTo {
	
	/**
	 * Metodo che effettua la conversione in stringa(formato json) di un oggetto una generica classe
	 * @param clas istanza di una generica classe
	 * @return stringa di oggetti in formato json
	 */
	public static String Json(Object clas) {
		Gson gson = new Gson();
		return gson.toJson(clas);
		
	}
}

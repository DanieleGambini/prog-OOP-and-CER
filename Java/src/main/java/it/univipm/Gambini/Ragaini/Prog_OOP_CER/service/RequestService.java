package it.univipm.Gambini.Ragaini.Prog_OOP_CER.service;

/**
 * Interfaccia dei metodi che gestiscono le richieste provenienti dalla classe RequestController
 */
public interface RequestService {
	
	/**
	 * Metodo che fornisce il dataset di riferimento parsato.
	 * @return Array di oggetti in formato json
	 */
	public abstract String dataRequest();
	
	/**
	 * Metodo che fornisce il dataset parsato corrispondente al filtro richiesto
	 * @param filter stringa formato json da specificare all'interno del body
	 * @return Array di oggetti in formato json
	 */
	public abstract String dataRequest(String filter);
	
	/** Metodo che fornisce i metadati relativi al dataset di riferimento
	 * @return array di oggetti in formato json
	 */
	public abstract String metadataRequest();
	
	/** Metodo che restituisce una pagina di help
	 * @return stringa in formato HTML file
	 */
	public abstract String rootRequest();
	
	/**Metodo che stampa una pagina di prova contenente gli standard di codifica degli array relativi ai tipi nativi della libreria "Java.lang"
	 * @return Array di oggetti in formato json
	 */
	public abstract String proofRequest();
	
	/**Metodo che fornisce le statistiche richieste su un sottoinsieme del dataset relativo al filtro immesso.
	 * @param filter stringa formato json da specificare all'interno del body
	 * @return Array di oggetti in formato json
	 */
	public abstract String statsRequestFilter(String filter);

}

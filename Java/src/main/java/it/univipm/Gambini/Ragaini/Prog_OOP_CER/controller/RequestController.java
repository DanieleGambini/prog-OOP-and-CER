package it.univipm.Gambini.Ragaini.Prog_OOP_CER.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.service.RequestService;


/**
 * Classe che gestisce le Rest API specificandone: rotta, metodo di chiamata (con eventuali parametri) e tipo di ritorno
 *
 */

@RestController
public class RequestController {
	@Autowired
	RequestService requestService;
	
	/**
	 * Metodo che definisce la rotta "/data" relativa al tipo di richiesta (GET).
	 * Restituisce al client http il dataset di riferimento.
	 * @return Array di oggetti in formato json
	 */
	@RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> dataRequest() {
		return new ResponseEntity<>(requestService.dataRequest(), HttpStatus.OK);
	}
	
	/**
	 * Metodo che definisce la rotta "/data" relativa al tipo di richiesta (POST).
	 * Restituisce al client http il dataset di riferimento parsato corrispondente al filtro richiesto.
	 * @param filter stringa formato json da specificare all'interno del body
	 * @return Array di oggetti in formato json
	 */
	@RequestMapping(value = "/data", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> dataRequest(@RequestBody String filter) {
		return new ResponseEntity<>(requestService.dataRequest(filter), HttpStatus.OK);
	}
	
	/**
	 * Metodo che definisce la rotta "/metadata" relativa al tipo di richiesta (GET). 
	 * Restituisce al client http i metadati relativi al dataset di riferimento
	 * @return Array di oggetti in formato json
	 */
	@RequestMapping(value = "/metadata", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> metadataRequest() {
		return new ResponseEntity<>(requestService.metadataRequest(), HttpStatus.OK);
	}
	
	/**
	 * Metodo che definisce la rotta "/" relativa al tipo di richiesta (GET).
	 * Restituisce al client http una pagina di help.
	 * @return HTML file
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
	public ResponseEntity<Object> rootRequest() {
		return new ResponseEntity<>(requestService.rootRequest(), HttpStatus.OK);
	}
	
	/**
	 * Metodo che definisce la rotta "/proof" relativa al tipo di richiesta (GET).
	 * Restituisce al client http una stampa di prova contenente gli standard di codifica degli array relativi ai tipi nativi della libreria "Java.lang"
	 * @return Array di oggetti in formato json 
	 */
	@RequestMapping(value = "/proof", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> proofRequest() {
		return new ResponseEntity<>(requestService.proofRequest(), HttpStatus.OK);
	}
	
	/**
	 * Metodo che definisce la rotta "/stats" relativa al tipo di richiesta (GET).
	 * Restituisce al client http le statistiche richieste sul dataset di riferimento
	 * @return Array di oggetti in formato json 
	 */
	@RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> statsRequestFilter() {
		return new ResponseEntity<>(requestService.statsRequestFilter(""), HttpStatus.OK);
	}
	
	/**
	 * Metodo che definisce la rotta "/stats" relativa al tipo di richiesta (POST). 
	 * Restituisce al client http le statistiche richieste su un sottoinsieme del dataset relativo al filtro immesso.
	 * @param filter stringa formato json
	 * @return Array di oggetti in formato json
	 */
	@RequestMapping(value = "/stats", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> statsRequestFilter(@RequestBody String filter) {
		return new ResponseEntity<>(requestService.statsRequestFilter(filter), HttpStatus.OK);
	}
}
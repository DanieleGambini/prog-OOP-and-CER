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
	 * Metodo che definisce la rotta "/data" specificando il tipo di richiesta (GET)
	 * @return Array di oggetti json (dataset parsato)
	 */
	@RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> dataRequest() {
		return new ResponseEntity<>(requestService.dataRequest(), HttpStatus.OK);
	}
	
	/**
	 * Metodo che definisce la rotta "/data" specificando il tipo di richiesta (POST) e il filtro da applicare
	 * @param filter formato json da specificare all'interno del body
	 * @return Array di oggetti json (dataset parsato corrispondente al filtro richiesto)
	 */
	@RequestMapping(value = "/data", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> dataRequest(@RequestBody String filter) {
		return new ResponseEntity<>(requestService.dataRequest(filter), HttpStatus.OK);
	}
	
	/**
	 * Metodo che definisce la rotta "/metadata" specificando il tipo di richiesta (GET)
	 * @return Array di oggetti json (metadati relativi al dataset)
	 */
	@RequestMapping(value = "/metadata", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> metadataRequest() {
		return new ResponseEntity<>(requestService.metadataRequest(), HttpStatus.OK);
	}
	
	/**
	 * Metodo che definisce la rotta "/" specificando il tipo di richiesta (GET)
	 * @return HTML file contenente istruzioni di help
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> rootRequest() {
		return new ResponseEntity<>(requestService.rootRequest(), HttpStatus.OK);
	}
	
	/**
	 * Metodo che definisce la rotta "/proof" specificando il tipo di richiesta (GET)
	 * @return Array di oggetti json relativi ai tipi nativi della libreria "Java.lang"
	 */
	@RequestMapping(value = "/proof", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> proofRequest() {
		return new ResponseEntity<>(requestService.proofRequest(), HttpStatus.OK);
	}
	
	/**
	 * Metodo che definisce la rotta "/stats" specificando il tipo di richiesta (GET)
	 * @return Array di oggetti json (contente statistiche del dataset)
	 */
	@RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> statsRequestFilter() {
		return new ResponseEntity<>(requestService.statsRequestFilter(""), HttpStatus.OK);
	}
	
	/**
	 * Metodo che definisce la rotta "/stats" specificando il tipo di richiesta (POST) e il filtro da applicare
	 * @param filter formato json
	 * @return Array di oggetti json (contente statistiche del dataset relative al filtro immesso)
	 */
	@RequestMapping(value = "/stats", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> statsRequestFilter(@RequestBody String filter) {
		return new ResponseEntity<>(requestService.statsRequestFilter(filter), HttpStatus.OK);
	}
}
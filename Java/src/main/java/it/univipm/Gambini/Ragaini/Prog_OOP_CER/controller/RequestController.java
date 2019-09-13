package it.univipm.Gambini.Ragaini.Prog_OOP_CER.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.service.RequestService;
import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Azure;

@RestController
public class RequestController {
	@Autowired
	RequestService requestService;
	
	@RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> dataRequest() {
		return new ResponseEntity<>(requestService.dataRequest(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/metadata", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> metadataRequest() {
		return new ResponseEntity<>(requestService.metadataRequest(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> statsRequest() {
		return new ResponseEntity<>(requestService.statsRequest(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> rootRequest() {
		return new ResponseEntity<>(requestService.rootRequest(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/proof", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> proofRequest() {
		return new ResponseEntity<>(requestService.proofRequest(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/connect", method = RequestMethod.GET, produces = "text/html")
	public ResponseEntity<Object> connectRequest() {
		return new ResponseEntity<>(requestService.connectRequest(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stats/geo={GEO}&obj={OBJ}", method = RequestMethod.GET)
	public ResponseEntity<Object> statsRequest(@PathVariable("GEO") String geo, @PathVariable("OBJ") String obj) {
		return new ResponseEntity<>(Azure.stats(geo,obj), HttpStatus.OK);
	}
	

}

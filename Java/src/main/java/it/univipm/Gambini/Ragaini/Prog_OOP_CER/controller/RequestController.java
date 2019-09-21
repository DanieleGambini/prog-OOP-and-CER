package it.univipm.Gambini.Ragaini.Prog_OOP_CER.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.service.RequestService;
//import it.univipm.Gambini.Ragaini.Prog_OOP_CER.utility.Azure;

@RestController
public class RequestController {
	@Autowired
	RequestService requestService;
	
	@RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> dataRequest() {
		return new ResponseEntity<>(requestService.dataRequest(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/data", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> dataRequest(@RequestBody String filter) {
		return new ResponseEntity<>(requestService.dataRequest(filter), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/metadata", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> metadataRequest() {
		return new ResponseEntity<>(requestService.metadataRequest(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> rootRequest() {
		return new ResponseEntity<>(requestService.rootRequest(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/proof", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> proofRequest() {
		return new ResponseEntity<>(requestService.proofRequest(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> statsRequestFilter() {
		return new ResponseEntity<>(requestService.statsRequestFilter(""), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stats", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> statsRequestFilter(@RequestBody String filter) {
		return new ResponseEntity<>(requestService.statsRequestFilter(filter), HttpStatus.OK);
	}
}
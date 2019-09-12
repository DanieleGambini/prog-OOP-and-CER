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
	
	@RequestMapping(value = "/average/start={start_year},end={end_year}", method = RequestMethod.GET)
	public ResponseEntity<Object> average(@PathVariable("start_year") int start_year, @PathVariable("end_year") int end_year
			/*@PathVariable("start_year") int start_year, @PathVariable("start_year") int start_year,
			@PathVariable("start_year") int start_year*/) {
		return new ResponseEntity<>(Azure.average(start_year, end_year), HttpStatus.OK);
	}
	

}

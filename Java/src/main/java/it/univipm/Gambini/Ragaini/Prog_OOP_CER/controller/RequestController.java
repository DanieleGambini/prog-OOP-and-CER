package it.univipm.Gambini.Ragaini.Prog_OOP_CER.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univipm.Gambini.Ragaini.Prog_OOP_CER.service.RequestService;

@RestController
public class RequestController {
	@Autowired
	RequestService requestService;
	
	@RequestMapping(value = "/data_request", method = RequestMethod.GET)
	public ResponseEntity<Object> dataRequest() {
		return new ResponseEntity<>(requestService.datasetRequest(/*dataset*/), HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/metadata_request", method = RequestMethod.GET)
//	public ResponseEntity<Object> metadataRequest() {
//		return new ResponseEntity<>(requestService.dataRequest(/*metadata*/), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/stats_request", method = RequestMethod.GET)
//	public ResponseEntity<Object> statsRequest() {
//		return new ResponseEntity<>(requestService.dataRequest(/*stats*/), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/dataset_request", method = RequestMethod.GET)
//	public ResponseEntity<Object> datasetRequest() {
//		return new ResponseEntity<>(requestService.dataRequest(/*dataset.json*/), HttpStatus.OK);
//	}
	
	/* ALTERNATIVAMENTE SI PUÓ FARE "/request/{data\metadata\stats\data}" 
	@RequestMapping(value = "/products/{r}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") int id,@RequestBody Product product) {
		productrequestService.updateProduct(id, product);
		return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
		*/

}

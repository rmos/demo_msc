package com.gfi.msc.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfi.msc.demo.dtos.EnvironmentsOut;
import com.gfi.msc.demo.services.IMSCService;

@RestController
public class DemoController {
	
	private static final String API_ENVIRONMENTS = "/demoEnv";
	
	@Autowired
	private IMSCService service;

	@GetMapping(path = API_ENVIRONMENTS + "/environments", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<EnvironmentsOut> listarEntornos() {
		
		EnvironmentsOut response = service.obtenerEntornos();
		
		HttpStatus statusResponse = HttpStatus.OK;
		
		return new ResponseEntity<EnvironmentsOut>(response, statusResponse);
		
	}

}

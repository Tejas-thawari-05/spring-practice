package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.util.AppUtils;

@RestController
@RequestMapping("/payment")
public class ControllerClass {
	
	private static final Logger LOG = LoggerFactory.getLogger(ControllerClass.class);

	@GetMapping("/dopay")
	public String getMethodName() {
		LOG.info("ENTER INTO PAYMENT PROCESS");
		try {
			LOG.info("PAYMENT ABOUT TO START");
			throw new RuntimeException("NO BALANCE EXCEPTION");
		} catch (Exception e) {
			LOG.error("UNABLE TO PROCESS PAYMENT "+e.getMessage());
			e.printStackTrace();
			
			AppUtils.getLogSupport(e);
		}
		
		return "SUCCESS";
	}
	
	
}

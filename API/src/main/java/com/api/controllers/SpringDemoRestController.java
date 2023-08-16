package com.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class SpringDemoRestController {
	@GetMapping("/ping")
    public ResponseEntity<String> helloWorld() {
		//HttpServletRequest request;
        return ResponseEntity.ok(String.format("ping success."));
    }
}

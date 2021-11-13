package com.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2Controller {
	
	@GetMapping("/")
	public String getLogin() {
		return "index";
	}

	@GetMapping("/resource")
	public String getResource() {
		return "resource";
	}
	
	@GetMapping("/res")
	public String getRes() {
		return "resource";
	}	
	
}

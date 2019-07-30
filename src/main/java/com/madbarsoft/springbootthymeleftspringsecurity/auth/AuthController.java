package com.madbarsoft.springbootthymeleftspringsecurity.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.madbarsoft.springbootthymeleftspringsecurity.book.BookEntity;

@Controller
public class AuthController {
	
	@GetMapping(value="/login")
	public String create(Model model){
		model.addAttribute("book", new BookEntity());
		return "auth/login";
	}
		
	
	@RequestMapping("/403")
	public String accessDenied(Model model) {
		model.addAttribute("book", new BookEntity());
		return "auth/accessDenie";
	}

}

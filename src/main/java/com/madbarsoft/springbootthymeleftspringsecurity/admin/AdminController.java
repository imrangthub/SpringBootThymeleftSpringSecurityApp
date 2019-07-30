package com.madbarsoft.springbootthymeleftspringsecurity.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.madbarsoft.springbootthymeleftspringsecurity.book.BookEntity;

@Controller
public class AdminController {
	
	
	@GetMapping(value="/admin")
	public String index(Model model){
		model.addAttribute("book", new BookEntity());
		return "admin/index";
	}

}

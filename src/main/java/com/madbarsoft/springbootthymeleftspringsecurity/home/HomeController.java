package com.madbarsoft.springbootthymeleftspringsecurity.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.madbarsoft.springbootthymeleftspringsecurity.book.BookEntity;
import com.madbarsoft.springbootthymeleftspringsecurity.book.BookService;

@Controller
public class HomeController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/home")
	public String home(Model model) {
		List<BookEntity> bookList = bookService.list();
		model.addAttribute("bookList", bookList);
		return "home";
	}

}

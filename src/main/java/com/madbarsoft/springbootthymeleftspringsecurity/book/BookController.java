package com.madbarsoft.springbootthymeleftspringsecurity.book;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;

	@GetMapping(value="/list")
	public String professionList(Model model){
		List<BookEntity> bookList = bookService.list();
		model.addAttribute("bookList", bookList);
		return "book/index";
	}
	

	@GetMapping(value="/view")
	public String view(Model model, @RequestParam Long id){
		BookEntity book = bookService.findById(id);
		model.addAttribute("book", book);
		return "book/view";
	}
	
	@GetMapping(value="/create")
	public String create(Model model){
		model.addAttribute("book", new BookEntity());
		return "book/create";
	}
	
	@GetMapping(value="/edit")
	public String editView(Model model, @RequestParam Long id){
		BookEntity book = bookService.findById(id);
		model.addAttribute("book", book);
		return "book/create";
	}
	
	
	@PostMapping(value="/create")
	public String save(Model model, BookEntity book){
		bookService.saveOrUpdate(book);
		return "redirect:/book/list";
	}
	
	
	@GetMapping(value="/remove")
	public String remove(Model model, @RequestParam Long id){
		bookService.removeById(id);
		List<BookEntity> bookList = bookService.list();
		model.addAttribute("bookList", bookList);
		return "book/index";
	}
	
	

}

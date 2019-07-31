package com.madbarsoft.springbootthymeleftspringsecurity.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.madbarsoft.springbootthymeleftspringsecurity.book.BookEntity;
import com.madbarsoft.springbootthymeleftspringsecurity.book.BookService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private BookService bookService;
	
	
	@GetMapping(value="/list")
	public String index(Model model){
		List<BookEntity> removeBooklist = bookService.removeBooklist();
		System.out.println("removeBooklist:"+removeBooklist);
		model.addAttribute("removeBooklist", removeBooklist);
		return "admin/index";
	}
	
	@GetMapping(value="/book-restore")
	public String restore(Model model, @RequestParam Long id){
		BookEntity obj  = bookService.findByIdAllItem(id);
		obj.setDeleted(false);
		bookService.saveOrUpdate(obj);
		List<BookEntity> removeBooklist = bookService.removeBooklist();
		model.addAttribute("removeBooklist", removeBooklist);
		return "admin/index";
	}
	
	@GetMapping(value="/book-delete")
	public String remove(Model model, @RequestParam Long id){
		bookService.deleteById(id);
		List<BookEntity> removeBooklist = bookService.removeBooklist();
		model.addAttribute("removeBooklist", removeBooklist);
		return "admin/index";
	}
	

}

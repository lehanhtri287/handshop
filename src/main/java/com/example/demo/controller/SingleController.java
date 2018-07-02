package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.Account;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CommentService;
import com.example.demo.service.ProductService;

@Controller
public class SingleController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "addComment", method = RequestMethod.POST)
	public String insertComment(String context, int idProduct, Model model, HttpSession session) {
		// Account account = (Account) session.getAttribute("user");
		// System.out.println(comment);
		// System.out.println(id);
		Product product = productService.getProduct(idProduct);
		Account account = (Account) session.getAttribute("user");
		
		Comment comment = new Comment(product, account, context);
		commentService.insertComment(comment);

		model.addAttribute("singleProduct", productService.getProduct(idProduct));
		model.addAttribute("listCate", categoryService.getAllCategories());
		model.addAttribute("listComments", commentService.getComments(idProduct));
		return "single";
	}

	@RequestMapping(value = "/single/{id}", method = RequestMethod.GET)
	public String singleProduct(@PathVariable int id, Model model) {
		model.addAttribute("singleProduct", productService.getProduct(id));
		model.addAttribute("listCate", categoryService.getAllCategories());
		model.addAttribute("listComments", commentService.getComments(id));

		return "single";
	}
}

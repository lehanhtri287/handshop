package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = { "/index", "/" }, method = RequestMethod.GET)
	public String productList(Model model) {
		model.addAttribute("listProducts", productService.getAllProduct());
		model.addAttribute("listCate", categoryService.getAllCategories());
		
		return "index";
	}

	@RequestMapping(value = "/viewByCate/{idCate}", method = RequestMethod.GET)
	public String listProductsByCate(@PathVariable int idCate, Model model) {
		model.addAttribute("listProducts", productService.getProductByCate(idCate));
		model.addAttribute("listCate", categoryService.getAllCategories());
		model.addAttribute("category", categoryService.getCategory(idCate));

		return "index";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchProduct(String input, Model model) {
		model.addAttribute("listProducts", productService.searchProducts(input));
		model.addAttribute("listCate", categoryService.getAllCategories());
//		model.addAttribute("category", categoryService.getCategory(idCate));

		return "index";
	}
	
}

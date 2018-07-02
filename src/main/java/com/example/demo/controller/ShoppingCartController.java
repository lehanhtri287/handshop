package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.Account;
import com.example.demo.entities.Order;
import com.example.demo.entities.Product;
import com.example.demo.model.ProductCart;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.mailService.EmailProperties;
import com.example.demo.service.mailService.MailEngine;

@SuppressWarnings("unchecked")
@Controller
public class ShoppingCartController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "shoppingcart", method = { RequestMethod.POST, RequestMethod.GET })
	public void addCart(int prodId, HttpSession session, Model model) {
		int result = 0;

		Product sanpham = productService.getProduct(prodId);

		if (session.getAttribute("list_detail") == null) {
			List<ProductCart> details = new ArrayList<>();
			ProductCart productCart = new ProductCart(sanpham, 1);
			details.add(productCart);
			session.setAttribute("list_detail", details);
			result = details.size();
			session.setAttribute("size", result);
		} else {
			List<ProductCart> details = (ArrayList<ProductCart>) session.getAttribute("list_detail");
			boolean tmp = false;
			for (ProductCart detail : details) {
				if (prodId == detail.getProduct().getIdProduct()) {
					detail.setQuantityCart(detail.getQuantityCart() + 1);
					tmp = true;
				}
			}
			if (!tmp) {
				details.add(new ProductCart(sanpham, 1));
			}
			result = details.size();
			session.setAttribute("list_detail", details);
			session.setAttribute("size", result);
			// System.out.println(details);
		}

	}

	@RequestMapping(value = "showCart", method = RequestMethod.GET)
	public String getCart(HttpSession session, Model model) {
		List<ProductCart> details = (ArrayList<ProductCart>) session.getAttribute("list_detail");
		int totalAmount = 0;
		Account account = (Account) session.getAttribute("user");
		if (details == null) {
			details = new ArrayList<>();
		}
		if (account != null)
			model.addAttribute("user", account);
		totalAmount = getTotalAmount(details);
		model.addAttribute("totalAmount", totalAmount);
		model.addAttribute("productsCart", details);
		model.addAttribute("listCate", categoryService.getAllCategories());
		session.setAttribute("size", details.size());

		return "shoppingCart";
	}

	@RequestMapping(value = "deletecart/{idProduct}", method = RequestMethod.GET)
	public String deleteProductCart(@PathVariable int idProduct, HttpSession session) {
		List<ProductCart> productCarts = (ArrayList<ProductCart>) session.getAttribute("list_detail");

		for (int i = productCarts.size() - 1; i >= 0; i--) {
			if (productCarts.get(i).getProduct().getIdProduct() == idProduct) {
				productCarts.remove(productCarts.get(i));
			}
		}
		session.setAttribute("list_detail", productCarts);
		session.setAttribute("size", productCarts.size());

		return "forward:/showCart";
	}

	@RequestMapping(value = "plusProduct/{idProduct}", method = RequestMethod.GET)
	public String plusProduct(@PathVariable int idProduct, HttpSession session) {
		List<ProductCart> productCarts = (ArrayList<ProductCart>) session.getAttribute("list_detail");
		for (int i = productCarts.size() - 1; i >= 0; i--) {
			if (productCarts.get(i).getProduct().getIdProduct() == idProduct) {
				if (productCarts.get(i).getQuantityCart() >= productCarts.get(i).getProduct().getQuantityProduct()) {
					productCarts.get(i).setQuantityCart(productCarts.get(i).getProduct().getQuantityProduct());
				} else {
					productCarts.get(i).setQuantityCart(productCarts.get(i).getQuantityCart() + 1);
				}
			}
		}
		session.setAttribute("list_detail", productCarts);
		session.setAttribute("size", productCarts.size());

		return "forward:/showCart";
	}

	@RequestMapping(value = "minusProduct/{idProduct}", method = RequestMethod.GET)
	public String minusProduct(@PathVariable int idProduct, HttpSession session) {
		List<ProductCart> productCarts = (ArrayList<ProductCart>) session.getAttribute("list_detail");
		for (int i = productCarts.size() - 1; i >= 0; i--) {
			if (productCarts.get(i).getProduct().getIdProduct() == idProduct) {
				if (productCarts.get(i).getQuantityCart() <= 1) {
					productCarts.get(i).setQuantityCart(1);
				} else {
					productCarts.get(i).setQuantityCart(productCarts.get(i).getQuantityCart() - 1);
				}
			}
		}
		session.setAttribute("list_detail", productCarts);
		session.setAttribute("size", productCarts.size());

		return "forward:/showCart";
	}

	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String payment(Order order, HttpSession session, Model model) {
		List<ProductCart> productCarts = (ArrayList<ProductCart>) session.getAttribute("list_detail");
		MailEngine mailEngine = new MailEngine();

		String resultMessage = "";

		int totalAmount = getTotalAmount(productCarts);
		order.setTotalAmount(totalAmount);
		order.setStatus(0);
		order.setOrderDate(new Date());

		boolean tmp = orderService.insertOrder(order);

		order.setIdOrder(orderService.getInsertedID());

		for (ProductCart productCart : productCarts) {
			orderService.insertOrderDetail(order.getIdOrder(), 
										   productCart.getProduct().getIdProduct(),
										   productCart.getQuantityCart());
		}
		model.addAttribute("listCate", categoryService.getAllCategories());

		String context = EmailProperties.MAIL_HEADER + "\n" + order.toString() + "\n" + EmailProperties.MAIL_FOOTER;
		if (tmp) {
			model.addAttribute("messageSuccess", order);
			mailEngine.sendEmail(order.getEmail(), EmailProperties.SUBJECT, context);
			session.setAttribute("list_detail", null);
		} else {
			resultMessage = "Đã có lỗi xảy ra!";
			model.addAttribute("messageFailed", resultMessage);
		}
		return "successPayment";
	}

	private int getTotalAmount(List<ProductCart> details) {
		int result = 0;

		for (ProductCart productCart : details) {
			result += productCart.getProduct().getPrice() * productCart.getQuantityCart();
		}

		return result;
	}
}

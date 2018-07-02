package com.example.demo.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Account;
import com.example.demo.entities.Confirmation;
import com.example.demo.model.AccountLogin;
import com.example.demo.model.AccountSignup;
import com.example.demo.service.AccountService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.mailService.EmailProperties;
import com.example.demo.service.mailService.MailEngine;

@Controller
@Scope("session")
@RequestMapping("/account/")
public class AccountController {

	@Autowired
	AccountService accountService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;

	@RequestMapping(value = "sign-up", method = RequestMethod.GET)
	public String signUpPage(Model model) {
		model.addAttribute("accountSignup", new AccountSignup());
		model.addAttribute("listCate", categoryService.getAllCategories());
		return "signUp";
	}

	@RequestMapping(value = "sign-up", method = RequestMethod.POST)
	public ModelAndView signUpProcess(@Validated AccountSignup accountSignup, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String emailAlreadyExists = null;
		Account account = null;
		String root = request.getContextPath();
		String host = request.getServerName();
		int port = request.getServerPort();
		MailEngine mailEngine = new MailEngine();
		
		if (accountSignup.getEmail().contains(" ")){
			bindingResult.rejectValue("email", "field.email.invalid");
		}
		else {
			emailAlreadyExists = accountService.findByEmail(accountSignup.getEmail());
			account = getAccountFromAccountSignup(accountSignup);
			if(emailAlreadyExists != null) bindingResult.rejectValue("email", "accSignup.email.exists");
		} 
		if (accountSignup.getPassword() != "" && accountSignup.getConfirmPassword() != ""){
			if (!accountSignup.getPassword().equals(accountSignup.getConfirmPassword())){
				bindingResult.rejectValue("password", "field.password.notMatch");
			}
		}
		if (bindingResult.hasErrors()) {
			mav.addObject("listCate", categoryService.getAllCategories());
			mav.setViewName("signUp");
		} else {
			if (accountService.signUp(account)) {
				int idCust = accountService.getNewIdCustomer();
				String token = new Timestamp(System.currentTimeMillis()).hashCode() + "";
				
				account.setId(idCust);
				Confirmation confirmation = new Confirmation(token, account, 0, 0);
				
				String link = "<a href=\"http://" + host + ":" + port + root + "/account/confirmUser/"+ idCust + "/" + token + "\">vào đây</a>";
				String context = EmailProperties.CONFIRM_CONTEXT + link + EmailProperties.CONFIRM_FOOTER;
				
				if(accountService.generateConfirm(confirmation)) {
					mailEngine.sendEmail(account.getEmail(), EmailProperties.CONFIRM_SUBJECT, context);
				}
				
				mav.addObject("successMess", "Đăng ký thành công");
				mav.addObject("accountSignup", new AccountSignup());
				mav.addObject("listCate", categoryService.getAllCategories());
				mav.setViewName("signUp");
			}
		}
		return mav;
	}

	public Account getAccountFromAccountSignup(AccountSignup accountSignup) {
		Account account = new Account(accountSignup.getEmail(), accountSignup.getPassword(),
				accountSignup.getFullName(), accountSignup.getAddress(), accountSignup.getPhoneNumber());
		return account;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		model.addAttribute("listCate", categoryService.getAllCategories());
		model.addAttribute("accountLogin", new Account());
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView loginProcess(@Validated AccountLogin accountLogin, BindingResult bindingResult, HttpSession session) {
			
		ModelAndView mav = new ModelAndView();
		Account account = null;
		
		if (accountLogin.getEmail().contains(" ")){
			bindingResult.rejectValue("email", "field.email.invalid");
		}
		else{
			if(accountLogin.getEmail() != "" && accountLogin.getPassword() != "") {
				account = accountService.findByEmailAndPassword(accountLogin);
				if (account == null)
					bindingResult.rejectValue("email", "accLogin.invalid");
				if (account != null && account.getConfirmation() == 0)
					bindingResult.rejectValue("email", "accLogin.notConfirmed");
			}
		}
		if (bindingResult.hasErrors()) {
			mav.addObject("listCate", categoryService.getAllCategories());
			mav.setViewName("login");
		} else {
			mav.addObject("listCate", categoryService.getAllCategories());
			mav.addObject("listProducts", productService.getAllProduct());
			mav.addObject("loginSuccess", "Login successful");

			if (account.getPosition().equalsIgnoreCase("ADM")) {
				session.setAttribute("admin", account);
				mav.setViewName("redirect:/admin/index");
			} else {
				session.setAttribute("user", account);
				mav.setViewName("redirect:/");
			}

		}
		return mav;
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request, HttpSession session){
		if(session.getAttribute("user") != null) {
			session.removeAttribute("user");
			return "redirect:/";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "forgot-password", method = RequestMethod.GET)
	public String forgotPasswordPage(Model model) {
		model.addAttribute("listCate", categoryService.getAllCategories());
		return "forgotPass";
	}
	
	@RequestMapping(value = "confirmUser/{idCust}/{token}", method = RequestMethod.GET)
	public String confirmAccount(@PathVariable int idCust, @PathVariable String token, Model model) {
		System.out.println(idCust + "-" + token);
		int temp = accountService.confirmAccount(idCust, token);
		
		if(temp == 0) {
			model.addAttribute("messageConfirm", "Đã xác nhận tài khoản thành công!");
		} else if(temp == 1) {
			model.addAttribute("messageConfirm", "Link đã hết hạn!");
		} else {
			model.addAttribute("messageConfirm", "Xin lỗi, link không tồn tại!");
		}
		
		return "result";
	}
	

}

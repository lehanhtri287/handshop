package com.example.demo.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entities.Account;
import com.example.demo.model.AccountPassUpdating;
import com.example.demo.service.AccountService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderService;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	AccountService accountService;
	@Autowired
	OrderService orderService;

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public String updateInfoPage(HttpSession session, @ModelAttribute("message") String message, Model model) {
		model.addAttribute("listCate", categoryService.getAllCategories());
		if (session.getAttribute("user") != null) {
			model.addAttribute("message", message);
			model.addAttribute("account", new Account());
			return "user/updateInfo";
		} else {
			model.addAttribute("message", "Bạn chưa đăng nhập");
			return "user/updateInfo";
		}
	}

	@RequestMapping(value = "info", method = RequestMethod.POST)
	public ModelAndView updateInfoProcess(@Validated Account account, BindingResult bindingResult, HttpSession session,
			RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("user") != null) {
			if (!account.getPassword().equals("")) {
				if (accountService.updateAccountInfo(account)) {
					redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
					session.setAttribute("user", account);
					mav.setViewName("redirect:/user/info");
				} else {
					bindingResult.rejectValue("password", "field.password.wrong");
				}
			} else {
				bindingResult.rejectValue("password", "field.empty");
			}
			if (bindingResult.hasErrors())
				mav.setViewName("user/updateInfo");
		} else {
			mav.addObject("message", "Bạn chưa đăng nhập");
			mav.setViewName("user/updateInfo");
		}
		return mav;
	}

	@RequestMapping(value = "info/password", method = RequestMethod.GET)
	public String updatePasswordPage(HttpSession session, @ModelAttribute("message") String message, Model model) {
		model.addAttribute("listCate", categoryService.getAllCategories());
		if (session.getAttribute("user") != null) {
			model.addAttribute("message", message);
			model.addAttribute("accountPassUpdating", new AccountPassUpdating());
			return "user/updatePassword";
		} else {
			model.addAttribute("message", "Bạn chưa đăng nhập");
			return "user/updatePassword";
		}
	}

	@RequestMapping(value = "info/password", method = RequestMethod.POST)
	public ModelAndView updatePasswordProcess(@Validated AccountPassUpdating accountPassUpdating,
			BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("user") != null) {
			if (accountPassUpdating.getNewPassword().equals(accountPassUpdating.getConfirmNewPassword())) {
				if(accountPassUpdating.getPassword() != ""){
					Account account = accountService.updateAccountPassWord(accountPassUpdating);
					if (account != null) {
						redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
						session.setAttribute("user", account);
						mav.setViewName("redirect:/user/info/password");
					} else {
						bindingResult.rejectValue("password", "field.password.wrong");
					}
				}
			} else {
				bindingResult.rejectValue("newPassword", "field.password.notMatch");
			}
			if (bindingResult.hasErrors())
				mav.setViewName("user/updatePassword");
		} else {
			mav.addObject("message", "Bạn chưa đăng nhập");
			mav.setViewName("user/updatePassword");
		}
		return mav;
	}

	@RequestMapping(value = "info/orders", method = RequestMethod.GET)
	public String orderHistoryPage(HttpSession session, @ModelAttribute("message") String message, Model model) {
		model.addAttribute("listCate", categoryService.getAllCategories());
		model.addAttribute("message", message);
		if (session.getAttribute("user") != null) {
			Account user = (Account) session.getAttribute("user");
			model.addAttribute("orderList", orderService.getOrdersByEmail(user.getEmail()));
			return "user/orderHistory";
		} else {
			model.addAttribute("message", "Bạn chưa đăng nhập");
			return "user/orderHistory";
		}
	}

	@RequestMapping(value = "info/orders/detail/{id}", method = RequestMethod.GET)
	public String orderDetail(HttpSession session, @PathVariable Integer id, @ModelAttribute("message") String message,
			Model model) {
		model.addAttribute("listCate", categoryService.getAllCategories());
		if (session.getAttribute("user") != null) {
			model.addAttribute("message", message);
			model.addAttribute("orderDetail", orderService.getOrderDetailById(id));
			model.addAttribute("order", orderService.getOrderById(id));
			return "user/orderDetail";
		} else {
			model.addAttribute("message", "Bạn chưa đăng nhập");
			return "user/orderDetail";
		}
	}

	@RequestMapping(value = "info/orders/cancel/{id}", method = RequestMethod.GET)
	public String cancelOrder(HttpSession session, @PathVariable Integer id, @ModelAttribute("message") String message,
			Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("listCate", categoryService.getAllCategories());
		if (session.getAttribute("user") != null) {
			if (orderService.cancelOrderById(id)) {
				redirectAttributes.addFlashAttribute("message", "Hủy đơn hàng thành công");
			} else {
				redirectAttributes.addFlashAttribute("message", "Chỉ hủy đơn hàng đang xác nhận");
			}
			return "redirect:/user/info/orders";
		} else {
			return "redirect:/user/info/orders";
		}
	}
}

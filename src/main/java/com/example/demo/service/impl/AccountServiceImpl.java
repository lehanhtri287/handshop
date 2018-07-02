package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Account;
import com.example.demo.entities.Confirmation;
import com.example.demo.model.AccountLogin;
import com.example.demo.model.AccountPassUpdating;
import com.example.demo.repository.AccountDAO;
import com.example.demo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO accountDAO;

	@Override
	public boolean signUp(Account account) {
		return accountDAO.signUp(account);
	}

	@Override
	public String findByEmail(String email) {
		return accountDAO.findByEmail(email);
	}

	@Override
	public Account findByEmailAndPassword(AccountLogin accountLogin) {
		return accountDAO.findByEmailAndPassword(accountLogin);
	}

	@Override
	public boolean updateAccountInfo(Account account) {
		return accountDAO.updateAccountInfo(account);
	}
	
	public int size() {
		return accountDAO.size();
	}

	@Override
	public Account updateAccountPassWord(AccountPassUpdating accountPassUpdating) {
		return accountDAO.updateAccountPassWord(accountPassUpdating);
	}

	@Override
	public int getNumPages(int pageSize) {
		return accountDAO.getNumPages(pageSize);
	}

	@Override
	public List<Account> getCustomersPagination(int page, int pageSize) {
		return accountDAO.getCustomersPagination(page, pageSize);
	}

	@Override
	public int getNewIdCustomer() {
		return accountDAO.getNewIdCustomer();
	}

	@Override
	public boolean generateConfirm(Confirmation confirmation) {
		return accountDAO.generateConfirm(confirmation);
	}

	@Override
	public int confirmAccount(int idCust, String token) {
		return accountDAO.confirmAccount(idCust, token);
	}

}

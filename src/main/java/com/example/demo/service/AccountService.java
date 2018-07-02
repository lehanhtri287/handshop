package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Account;
import com.example.demo.entities.Confirmation;
import com.example.demo.model.AccountLogin;
import com.example.demo.model.AccountPassUpdating;

public interface AccountService {
	public boolean signUp(Account account);

	public String findByEmail(String email);

	public Account findByEmailAndPassword(AccountLogin accountLogin);

	public boolean updateAccountInfo(Account account);

	public Account updateAccountPassWord(AccountPassUpdating accountPassUpdating);

	public int size();

	public int getNumPages(int pageSize);

	public List<Account> getCustomersPagination(int page, int pageSize);

	public int getNewIdCustomer();

	public boolean generateConfirm(Confirmation confirmation);

	public int confirmAccount(int idCust, String token);
}

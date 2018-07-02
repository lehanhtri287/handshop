package com.example.demo.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Account;
import com.example.demo.entities.Confirmation;
import com.example.demo.hibernate.HibernateUtil;
import com.example.demo.model.AccountLogin;
import com.example.demo.model.AccountPassUpdating;
import com.example.demo.repository.AccountDAO;

@Repository
@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
public class AccountDAOImpl implements AccountDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDAOImpl.class);

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public boolean signUp(Account account) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String fullName = account.getFullName();
		fullName = fullName.replaceAll("\\s+", " "); // replace spaces to space
														// between
		fullName = fullName.replaceAll("(^\\s+|\\s+$)", ""); // remove spaces at
																// left and
																// right
		account.setFullName(fullName);
		account.setPassword(encoder.encode(account.getPassword()));
		account.setPosition("KH");
		account.setConfirmation(0);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(account);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			LOGGER.error("- error when call method signUp with paramater " + account, e);
			session.getTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}

	@Override
	public String findByEmail(String email) {
		String emailResult = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<String> query = session.createQuery("select email from Account where email = :email", String.class);
		query.setParameter("email", email);
		try {
			Optional<String> result = query.uniqueResultOptional();
			if (result.isPresent())
				emailResult = result.get();
		} catch (NoSuchElementException e) {
			LOGGER.error("- error when call method findByEmail with paramater " + email, e);
		} finally {
			session.close();
		}
		return emailResult;
	}

	@Override
	public Account findByEmailAndPassword(AccountLogin accountLogin) {
		Account accountResult = null;

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query<Account> query = session.createQuery("from " + Account.class.getName() + " where email = :email",
				Account.class);

		query.setParameter("email", accountLogin.getEmail());
		try {
			Optional<Account> result = query.uniqueResultOptional();
			accountResult = (Account) result.get();

			if (result.isPresent()) {
				if (!encoder.matches(accountLogin.getPassword(), accountResult.getPassword())) {
					accountResult = null;
				}
			}
		} catch (NoSuchElementException e) {
			LOGGER.error("- error when call method findByEmailAndPassword with paramater " + accountResult, e);
		} finally {
			session.close();
		}
		return accountResult;
	}

	@Override
	public boolean updateAccountInfo(Account account) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Account accountResult = null;
		boolean hasChanged = false;
		boolean result = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query<Account> query = session.createQuery("from Account where email = :email", Account.class);
		query.setParameter("email", account.getEmail());
		Optional<Account> accountRresult = query.uniqueResultOptional();
		try {
			if (accountRresult.isPresent()) {
				accountResult = accountRresult.get();
				if (encoder.matches(account.getPassword(), accountResult.getPassword())) {
					if (!account.getFullName().equals("")) {
						accountResult.setFullName(account.getFullName());
						hasChanged = true;
					}
					if (!account.getPhoneNumber().equals("")){
						accountResult.setPhoneNumber(account.getPhoneNumber());
						hasChanged = true;
					}
					if (!account.getAddress().equals("")) {
						accountResult.setAddress(account.getAddress());
						hasChanged = true;
					}
					if(hasChanged){
						session.save(accountResult);
						session.getTransaction().commit();
						result = true;
					}
				}
			}
		} catch (NoSuchElementException e) {
			LOGGER.error("- error when call method updateAccountInfo with paramater " + account, e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return result;
		
	}
	
//	@Override
//	public boolean updateAccountInfo(Account account) {
//
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		Account accountResult = null;
//		boolean hasChanged = false;
//		boolean result = false;
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//
//		Query<Account> query = session.createQuery("from Account where email = :email", Account.class);
//		query.setParameter("email", account.getEmail());
//		Optional<Account> accountRresult = query.uniqueResultOptional();
//		try {
//			if (accountRresult.isPresent()) {
//				accountResult = accountRresult.get();
//				if (encoder.matches(account.getPassword(), accountResult.getPassword())) {
//					if (!account.getFullName().equals("") && !accountResult.getFullName().equals(account.getFullName())) {
//						accountResult.setFullName(account.getFullName());
//						hasChanged = true;
//					}
//					if (!account.getPhoneNumber().equals("")
//							&& !accountResult.getPhoneNumber().equals(account.getPhoneNumber())) {
//						accountResult.setPhoneNumber(account.getPhoneNumber());
//						hasChanged = true;
//					}
//					if (!account.getAddress().equals("") && !accountResult.getAddress().equals(account.getAddress())) {
//						accountResult.setAddress(account.getAddress());
//						hasChanged = true;
//					}
//					if(hasChanged){
//						session.save(accountResult);
//						session.getTransaction().commit();
//						LOGGER.info("updated info");
//						result = true;
//					}
//				}else{
//					System.out.println("password wrong");
//				}
//			}
//		} catch (NoSuchElementException e) {
//			LOGGER.error("- error when call method updateAccountInfo with paramater " + account, e);
//			session.getTransaction().rollback();
//		} finally {
//			session.close();
//		}
//		return result;
//		
//	}

	@Override
	public Account updateAccountPassWord(AccountPassUpdating accountPassUpdating) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Account accountResult = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query<Account> query = session.createQuery("from Account where email = :email", Account.class);
		query.setParameter("email", accountPassUpdating.getEmail());
		try {
			Optional<Account> result = query.uniqueResultOptional();
			if (result.isPresent()) {
				accountResult = result.get();
				if (encoder.matches(accountPassUpdating.getPassword(), accountResult.getPassword())) {
					accountResult.setPassword(encoder.encode(accountPassUpdating.getNewPassword()));
					session.save(accountResult);
					session.getTransaction().commit();
					return accountResult;
				} else {
					accountResult = null;
				}
			}
		} catch (NoSuchElementException e) {
			LOGGER.error("- error when call method updateAccountInfo with paramater " + accountPassUpdating, e);
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return accountResult;
	}

	@Override
	public int size() {
		Session session = sessionFactory.openSession();
		int res = 0;
		try {
			session.getTransaction().begin();

			Query query = session.createSQLQuery("select count(*) as size from taikhoan where chucvu = 'KH'")
					.addScalar("size", new IntegerType());

			res = (int) query.uniqueResult();

			session.getTransaction().commit();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return res;

	}

	@Override
	public int getNumPages(int pageSize) {
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();

			Query query = session.createSQLQuery("select count(*) as numPages from taikhoan where chucvu = 'KH'")
					.addScalar("numPages", new IntegerType());

			int res = (int) query.uniqueResult();
			int numPages = res / pageSize;
			if (res % pageSize > 0) {
				numPages += 1;
			}

			session.getTransaction().commit();
			return numPages;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return 0;
	}

	@Override
	public List<Account> getCustomersPagination(int page, int pageSize) {
		List<Account> accounts = new ArrayList<>();

		Session session = sessionFactory.openSession();

		int offset = 0;

		if (page > getNumPages(pageSize)) {
			page = getNumPages(pageSize);
		}
		if (page <= 0) {
			page = 1;
		}
		offset = (page - 1) * pageSize;
		try {
			session.getTransaction().begin();
			String hql = "from " + Account.class.getName() + " where chucvu = 'KH' order by id_tk desc";
			Query query = session.createQuery(hql);
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
			accounts = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return accounts;
	}

	@Override
	public int getNewIdCustomer() {
		Session session = sessionFactory.openSession();
		int res = 0;
		try {
			session.getTransaction().begin();

			Query query = session.createSQLQuery("select id_tk from taikhoan order by id_tk desc limit 1");

			res = (int) query.uniqueResult();

			session.getTransaction().commit();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public boolean generateConfirm(Confirmation confirmation) {
		Session session = sessionFactory.openSession();

		try {
			session.getTransaction().begin();
			session.save(confirmation);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public int confirmAccount(int idCust, String token) {
		Session session = sessionFactory.openSession();
		int res = 0;
		try {
			session.getTransaction().begin();

			Query query = session.createSQLQuery("select checkConfirm(:token, :idCust) as result").addScalar("result",
					new IntegerType());
			query.setParameter("token", token);
			query.setParameter("idCust", idCust);

			res = (int) query.uniqueResult();

			session.getTransaction().commit();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return res;
	}
}

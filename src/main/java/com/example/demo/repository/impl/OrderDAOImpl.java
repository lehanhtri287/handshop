package com.example.demo.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Chitietdonhang;
import com.example.demo.entities.ChitietdonhangId;
import com.example.demo.entities.Order;
import com.example.demo.entities.Product;
import com.example.demo.hibernate.HibernateUtil;
import com.example.demo.repository.OrderDAO;

@Repository
@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
public class OrderDAOImpl implements OrderDAO {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public List<Order> getListOrders() {
		return null;
	}

	@Override
	public boolean insertOrder(Order order) {
		Session session = sessionFactory.openSession();

		try {
			session.getTransaction().begin();
			session.save(order);
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
	public boolean updateOrder(Order order) {
		Session session = sessionFactory.openSession();

		try {
			session.getTransaction().begin();

			Query query = session.createQuery("from " + Order.class.getName() + " where ID_DONHANG := idDonhang");
			query.setInteger("idDonhang", order.getIdOrder());
			Order dh = (Order) query.uniqueResult();
			dh.setOrderDate(order.getOrderDate());
			dh.setAddress(order.getAddress());
			dh.setEmail(order.getEmail());
			dh.setPhoneNumber(order.getPhoneNumber());
			dh.setStatus(order.getStatus());
			dh.setCustomerName(order.getCustomerName());
			dh.setTotalAmount(order.getTotalAmount());

			session.save(dh);
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
	public boolean deleteOrder(int idOrder) {
		return false;
	}

	public Order getOrder(int idDonhang) {
		Session session = sessionFactory.openSession();
		Order dh = null;

		try {
			session.getTransaction().begin();

			Query query = session.createQuery("from " + Order.class.getName() + " where ID_DONHANG = :idDonhang");
			query.setInteger("idDonhang", idDonhang);
			dh = (Order) query.uniqueResult();

			session.getTransaction().commit();
			return dh;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return dh;
	}

	@Override
	public int getInsertedID() {
		Session session = sessionFactory.openSession();
		int res = 0;
		try {
			session.getTransaction().begin();

			Query query = session.createSQLQuery("select id_donhang from donhang order by id_donhang desc limit 1");

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
	public boolean insertOrderDetail(int idDonhang, int idSanpham, int soLuong) {
		Session session = sessionFactory.openSession();

		try {
			session.getTransaction().begin();

			Order order = (Order) session.get(Order.class, idDonhang);
			Product sanpham = (Product) session.get(Product.class, idSanpham);
			ChitietdonhangId chitietdonhangId = new ChitietdonhangId(idDonhang, idSanpham, soLuong);

			session.save(new Chitietdonhang(chitietdonhangId, order, sanpham));

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

	public List<Chitietdonhang> getOrderDetail() {
		List<Chitietdonhang> orderDetails = new ArrayList<>();

		Session session = sessionFactory.openSession();

		try {
			session.getTransaction().begin();
			String hql = "from " + Chitietdonhang.class.getName();
			Query query = session.createQuery(hql);
			orderDetails = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return orderDetails;
	}

	@Override
	public int size() {
		Session session = sessionFactory.openSession();
		int res = 0;
		try {
			session.getTransaction().begin();

			Query query = session.createSQLQuery("select count(*) as size from donhang").addScalar("size",
					new IntegerType());

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
	public List<Order> getNewOrders() {
		List<Order> orders = new ArrayList<>();

		// Session session = sessionFactory.getCurrentSession();
		Session session = sessionFactory.openSession();

		try {
			session.getTransaction().begin();
			String hql = "from " + Order.class.getName() + " order by orderDate desc";
			Query query = session.createQuery(hql);
			query.setMaxResults(5);
			orders = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return orders;
	}

	@Override
	public List<Order> getOrdersByEmail(String email) {
		List<Order> orderList = null;
		Session session = sessionFactory.openSession();
		try {
			String hql = "from " + Order.class.getName() + " where email = :email";
			org.hibernate.query.Query<Order> query = session.createQuery(hql, Order.class);
			query.setParameter("email", email);
			orderList = query.getResultList();
		} catch (Exception e) {
			session.close();
		}
		return orderList;
	}

	@Override
	public List<Chitietdonhang> getOrderDetailById(Integer orderId) {
		List<Chitietdonhang> orderDetailList = null;
		Session session = sessionFactory.openSession();
		try {
			String hql = "from " + Chitietdonhang.class.getName() + " ctdh where ctdh.donhang.idOrder = :orderId";
			org.hibernate.query.Query<Chitietdonhang> query = session.createQuery(hql, Chitietdonhang.class);
			query.setParameter("orderId", orderId);
			orderDetailList = query.getResultList();
		} catch (Exception e) {
			session.close();
		}
		return orderDetailList;
	}

	@Override
	public Order getOrderById(Integer orderId) {
		Order order = null;
		Session session = sessionFactory.openSession();
		try {
			String hql = "from " + Order.class.getName() + " where idOrder = :orderId";
			org.hibernate.query.Query<Order> query = session.createQuery(hql, Order.class);
			query.setParameter("orderId", orderId);
			Optional<Order> result = query.uniqueResultOptional();
			if (result.isPresent())
				order = result.get();
		} catch (Exception e) {
			session.close();
		}
		return order;
	}

	@Override
	public boolean cancelOrderById(Integer orderId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			String hql = "update " + Order.class.getName()
					+ " set status = 3 where idOrder = :orderId and status = 0";
			org.hibernate.query.Query query = session.createQuery(hql);
			query.setParameter("orderId", orderId);
			int effectedRows = query.executeUpdate();
			session.getTransaction().commit();
			return 1 == effectedRows;
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

	@Override
	public List<Order> getOrdersPagination(int page, int pageSize) {
		List<Order> orders = new ArrayList<>();

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
			String hql = "from " + Order.class.getName() + " order by id_donhang desc";
			Query query = session.createQuery(hql);
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
			orders = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return orders;
	}

	@Override
	public int getNumPages(int pageSize) {
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();

			Query query = session.createSQLQuery("select count(*) as numPages from donhang").addScalar("numPages",
					new IntegerType());

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
	public boolean updateStatusOrder(int idDonhang, int status) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			String hql = "update " + Order.class.getName() + " set status = :status where idOrder = :orderId";
			org.hibernate.query.Query query = session.createQuery(hql);
			query.setParameter("orderId", idDonhang);
			query.setParameter("status", status);
			int effectedRows = query.executeUpdate();
			session.getTransaction().commit();
			return 1 == effectedRows;
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}
	
}

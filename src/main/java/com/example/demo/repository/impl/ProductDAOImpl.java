package com.example.demo.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.hibernate.HibernateUtil;
import com.example.demo.repository.ProductDAO;

@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
@Repository
public class ProductDAOImpl implements ProductDAO {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public List<Product> getAllProduct() {
		List<Product> sanphams = new ArrayList<>();

		// Session session = sessionFactory.getCurrentSession();
		Session session = sessionFactory.openSession();

		try {
			session.getTransaction().begin();
			String hql = "from " + Product.class.getName() + " where tinh_trang = 0";
			Query query = session.createQuery(hql);
			sanphams = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return sanphams;
	}

	public List<Product> getProductsPagination(int page, int pageSize) {
		List<Product> sanphams = new ArrayList<>();

		Session session = sessionFactory.openSession();

		int offset = 0;

		if (page > getNumpPages(pageSize)) {
			page = getNumpPages(pageSize);
		}
		if (page <= 0) {
			page = 1;
		}
		offset = (page - 1) * pageSize;
		try {
			session.getTransaction().begin();
			String hql = "from " + Product.class.getName() + " where tinh_trang = 0 order by id_sanpham desc";
			Query query = session.createQuery(hql);
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
			sanphams = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return sanphams;
	}

	public Product getProduct(int id) {
		Session session = sessionFactory.openSession();
		Product result = null;
		try {
			session.getTransaction().begin();
			String hql = "from " + Product.class.getName() + " where ID_SANPHAM = :idSanpham";
			Query query = session.createQuery(hql);
			query.setInteger("idSanpham", id);
			result = (Product) query.uniqueResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return result;
	}

	public boolean insertProduct(Product sanpham) {
		Session session = sessionFactory.openSession();

		try {
			session.getTransaction().begin();

			Query query = session.createQuery("from " + Category.class.getName() + " where ID_LOAIHANG = :ID_LOAIHANG");
			query.setInteger("ID_LOAIHANG", sanpham.getCategory().getIdCategory());
			Category cate = (Category) query.uniqueResult();
			sanpham.setCategory(cate);

			session.save(sanpham);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return false;
	}

	public boolean updateProduct(Product sanpham) {
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();
			Product sp = (Product) session.get(Product.class, sanpham.getIdProduct());

			sp.setNameProduct(sanpham.getNameProduct());
			sp.setPrice(sanpham.getPrice());
			sp.setSale(sanpham.getSale());
			sp.setStatus(sanpham.getStatus());
			sp.setDescription(sanpham.getDescription());
			sp.setQuantityProduct(sanpham.getQuantityProduct());

			Query query = session.createQuery("from " + Category.class.getName() + " where ID_LOAIHANG = :ID_LOAIHANG");
			query.setInteger("ID_LOAIHANG", sanpham.getCategory().getIdCategory());
			Category cate = (Category) query.uniqueResult();
			sp.setCategory(cate);

			session.update(sp);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return false;
	}

	public boolean deleteProduct(int idSanpham) {
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();

			Query query = session.createSQLQuery("call delete_sanpham(:idSanpham)");
			query.setParameter("idSanpham", idSanpham);
			int res = query.executeUpdate();

			session.getTransaction().commit();
			return 1 == res;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return false;
	}

	public int getNumpPages(int pageSize) {
		Session session = sessionFactory.openSession();
		try {
			session.getTransaction().begin();

			Query query = session.createSQLQuery("select count(*) as numPages from sanpham where tinh_trang = 0")
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

	public List<Product> getProductByCate(int id) {
		List<Product> sanphams = new ArrayList<>();
		Session session = sessionFactory.openSession();

		try {
			session.getTransaction().begin();
			String hql = "from " + Product.class.getName() + " where id_loaihang = :idCate";
			Query query = session.createQuery(hql);
			query.setInteger("idCate", id);

			sanphams = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return sanphams;
	}

	@Override
	public int size() {
		Session session = sessionFactory.openSession();
		int res = 0;
		try {
			session.getTransaction().begin();

			Query query = session.createSQLQuery("select count(*) as size from sanpham where tinh_trang = 0")
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
	public List<Product> getNewProducts() {
		List<Product> sanphams = new ArrayList<>();

		// Session session = sessionFactory.getCurrentSession();
		Session session = sessionFactory.openSession();

		try {
			session.getTransaction().begin();
			String hql = "from " + Product.class.getName() + " where tinh_trang = 0 order by id_sanpham desc";
			Query query = session.createQuery(hql);
			query.setMaxResults(5);
			sanphams = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return sanphams;
	}

	@Override
	public List<Product> searchProducts(String input) {
		List<Product> products = new ArrayList<>();
		Session session = sessionFactory.openSession();

		try {
			session.getTransaction().begin();
			String hql = "from " + Product.class.getName() + " where ten_sanpham like :input and tinh_trang = 0";
			Query query = session.createQuery(hql);
			query.setParameter("input", "%" + input + "%");
			products = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return products;
	}

}

package com.example.demo.repository;

import java.util.List;

import com.example.demo.entities.Product;

public interface ProductDAO {
	
	public List<Product> getAllProduct();

	public List<Product> getProductsPagination(int page, int pageSize);

	public Product getProduct(int id);

	public boolean insertProduct(Product sanpham);
	
	public boolean updateProduct(Product sanpham);

	public boolean deleteProduct(int idSanpham);
	
	public int getNumpPages(int pageSize);
	
	public int size();
	
	public List<Product> getProductByCate(int idLoaihang);

	public List<Product> getNewProducts();

	public List<Product> searchProducts(String input);

}

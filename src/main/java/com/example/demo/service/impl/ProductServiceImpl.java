package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Product;
import com.example.demo.repository.ProductDAO;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO productDAO;

	@Override
	public boolean addProduct(Product sanpham) {
		return productDAO.insertProduct(sanpham);
	}

	@Override
	public boolean editProduct(Product sanpham) {
		return productDAO.updateProduct(sanpham);
	}

	@Override
	public boolean deleteProduct(int idSanpham) {
		return productDAO.deleteProduct(idSanpham);
	}

	@Override
	public List<Product> getAllProduct() {
		return productDAO.getAllProduct();
	}

	@Override
	public Product getProduct(int idProduct) {
		return productDAO.getProduct(idProduct);
	}

	@Override
	public List<Product> getProductByCate(int idLoaihang) {
		return productDAO.getProductByCate(idLoaihang);
	}

	@Override
	public int size() {
		return productDAO.size();
	}

	@Override
	public List<Product> getNewProducts() {
		return productDAO.getNewProducts();
	}

	@Override
	public List<Product> getProductsPagination(int page, int pageSize) {
		return productDAO.getProductsPagination(page, pageSize);
	}

	@Override
	public int getNumPages(int pageSize) {
		return productDAO.getNumpPages(pageSize);
	}

	@Override
	public List<Product> searchProducts(String input) {
		return productDAO.searchProducts(input);
	}

}

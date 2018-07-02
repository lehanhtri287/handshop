package com.example.demo.model;

import com.example.demo.entities.Product;

public class ProductCart {
	private Product product;
	private int quantityCart;

	public ProductCart(Product product, int quantityCart) {
		super();
		this.product = product;
		this.quantityCart = quantityCart;
	}

	public ProductCart() {
		// TODO Auto-generated constructor stub
	}

	public int getQuantityCart() {
		return quantityCart;
	}

	public void setQuantityCart(int quantityCart) {
		this.quantityCart = quantityCart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductCart [product=" + product + ", quantityCart=" + quantityCart + "]";
	}

}

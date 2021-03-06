package com.example.demo.entities;
// Generated May 23, 2018 1:38:46 AM by Hibernate Tools 5.2.3.Final

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Sanpham generated by hbm2java
 */
@Entity
@Table(name = "sanpham", catalog = "shophandmade")
public class Product implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idProduct;
	private Category category;
	private String nameProduct;
	private int price;
	private String description;
	private int status;
	private Integer sale;
	private String images;
	private Integer quantityProduct;

	public Product() {
	}

	public Product(String nameProduct, int price, int status) {
		this.nameProduct = nameProduct;
		this.price = price;
		this.status = status;
	}

	public Product(Category category, String nameProduct, int price, String description, int status, Integer sale,
			String images, Integer quantityProduct) {
		this.category = category;
		this.nameProduct = nameProduct;
		this.price = price;
		this.description = description;
		this.status = status;
		this.sale = sale;
		this.images = images;
		this.quantityProduct = quantityProduct;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_SANPHAM", unique = true, nullable = false)
	public Integer getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(Integer idSanpham) {
		this.idProduct = idSanpham;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_LOAIHANG")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category loaihang) {
		this.category = loaihang;
	}
	@Column(name = "TEN_SANPHAM", nullable = false, length = 200)
	public String getNameProduct() {
		return this.nameProduct;
	}

	public void setNameProduct(String tenSanpham) {
		this.nameProduct = tenSanpham;
	}

	@Column(name = "GIA", nullable = false)
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int gia) {
		this.price = gia;
	}

	@Column(name = "MO_TA", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TINH_TRANG", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "GIAM_GIA")
	public Integer getSale() {
		return this.sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
	}

	@Column(name = "IMAGES", length = 500)
	public String getImages() {
		return this.images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Column(name = "SO_LUONG")
	public Integer getQuantityProduct() {
		return this.quantityProduct;
	}

	public void setQuantityProduct(Integer quantityProduct) {
		this.quantityProduct = quantityProduct;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", category=" + category + ", nameProduct=" + nameProduct
				+ ", price=" + price + ", description=" + description + ", status=" + status + ", sale=" + sale
				+ ", images=" + images + ", quantityProduct=" + quantityProduct + "]";
	}

}

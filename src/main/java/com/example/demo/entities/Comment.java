package com.example.demo.entities;
// Generated May 23, 2018 1:38:46 AM by Hibernate Tools 5.2.3.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Comment generated by hbm2java
 */
@Entity
@Table(name = "cmt", catalog = "shophandmade")
public class Comment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idCmt;
	private Product product;
	private Account account;
	private Date timeCmt;
	private String context;

	public Comment() {
	}

	public Comment(Product sanpham, Account taikhoan, Date thoiGian) {
		this.product = sanpham;
		this.account = taikhoan;
		this.timeCmt = thoiGian;
	}

	public Comment(Product sanpham, Account taikhoan, String noiDung) {
		this.product = sanpham;
		this.account = taikhoan;
		this.context = noiDung;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_CMT", unique = true, nullable = false)
	public Integer getIdCmt() {
		return this.idCmt;
	}

	public void setIdCmt(Integer idCmt) {
		this.idCmt = idCmt;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SANPHAM", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TK", nullable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "THOI_GIAN", nullable = false, length = 19)
	public Date getTimeCmt() {
		return this.timeCmt;
	}

	public void setTimeCmt(Date timeCmt) {
		this.timeCmt = timeCmt;
	}

	@Column(name = "NOI_DUNG", length = 500)
	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "Comment [idCmt=" + idCmt + ", product=" + product + ", account=" + account + ", timeCmt=" + timeCmt
				+ ", context=" + context + "]";
	}

}
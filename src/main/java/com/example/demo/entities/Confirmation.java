package com.example.demo.entities;
// Generated May 23, 2018 1:38:46 AM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Confirmation generated by hbm2java
 */
@Entity
@Table(name = "confirmation", catalog = "shophandmade")
public class Confirmation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String idConfirm;
	private Account taikhoan;
	private int status;
	private Date dateCreated;
	private Integer typeConfirmed;

	public Confirmation() {
	}

	public Confirmation(String idConfirm, Account taikhoan, int status, Date dateCreated) {
		this.idConfirm = idConfirm;
		this.taikhoan = taikhoan;
		this.status = status;
		this.dateCreated = dateCreated;
	}

	public Confirmation(String idConfirm, Account taikhoan, int status, Integer typeConfirmed) {
		this.idConfirm = idConfirm;
		this.taikhoan = taikhoan;
		this.status = status;
		this.typeConfirmed = typeConfirmed;
	}

	@Id

	@Column(name = "ID_CONFIRM", unique = true, nullable = false, length = 100)
	public String getIdConfirm() {
		return this.idConfirm;
	}

	public void setIdConfirm(String idConfirm) {
		this.idConfirm = idConfirm;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TK", nullable = false)
	public Account getTaikhoan() {
		return this.taikhoan;
	}

	public void setTaikhoan(Account taikhoan) {
		this.taikhoan = taikhoan;
	}

	@Column(name = "STATUS", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED", nullable = false, length = 19)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "TYPE_CONFIRMED")
	public Integer getTypeConfirmed() {
		return this.typeConfirmed;
	}

	public void setTypeConfirmed(Integer typeConfirmed) {
		this.typeConfirmed = typeConfirmed;
	}

}

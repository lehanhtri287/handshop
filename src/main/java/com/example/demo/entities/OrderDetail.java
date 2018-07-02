package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chitietdonhang", catalog = "shophandmade")
public class OrderDetail {
	private Integer idDonhang;
	private Integer idSanpham;
	private Integer soLuong;

	public OrderDetail() {
	}

	public OrderDetail(Integer idDonhang, Integer idSanpham, Integer soLuong) {
		super();
		this.idDonhang = idDonhang;
		this.idSanpham = idSanpham;
		this.soLuong = soLuong;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DONHANG")
	public Integer getIdDonhang() {
		return idDonhang;
	}

	public void setIdDonhang(Integer idDonhang) {
		this.idDonhang = idDonhang;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SANPHAM")
	public Integer getIdSanpham() {
		return idSanpham;
	}

	public void setIdSanpham(Integer idSanpham) {
		this.idSanpham = idSanpham;
	}

	@Column(name = "SO_LUONG")
	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "OrderDetail [idDonhang=" + idDonhang + ", idSanpham=" + idSanpham + ", soLuong=" + soLuong + "]";
	}

}

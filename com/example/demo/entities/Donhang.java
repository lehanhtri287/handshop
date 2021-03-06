package com.example.demo.entities;
// Generated May 2, 2018 12:42:50 PM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Donhang generated by hbm2java
 */
@Entity
@Table(name = "donhang", catalog = "shophandmade")
public class Donhang implements java.io.Serializable {

	private Integer idDonhang;
	private Date ngayDh;
	private Integer tongTien;
	private String tenKhachhang;
	private String sdt;
	private String diachi;
	private String email;
	private Integer status;
	private Set chitietdonhangs = new HashSet(0);

	public Donhang() {
	}

	public Donhang(String tenKhachhang) {
		this.tenKhachhang = tenKhachhang;
	}

	public Donhang(Date ngayDh, Integer tongTien, String tenKhachhang, String sdt, String diachi, String email,
			Integer status, Set chitietdonhangs) {
		this.ngayDh = ngayDh;
		this.tongTien = tongTien;
		this.tenKhachhang = tenKhachhang;
		this.sdt = sdt;
		this.diachi = diachi;
		this.email = email;
		this.status = status;
		this.chitietdonhangs = chitietdonhangs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_DONHANG", unique = true, nullable = false)
	public Integer getIdDonhang() {
		return this.idDonhang;
	}

	public void setIdDonhang(Integer idDonhang) {
		this.idDonhang = idDonhang;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NGAY_DH", length = 19)
	public Date getNgayDh() {
		return this.ngayDh;
	}

	public void setNgayDh(Date ngayDh) {
		this.ngayDh = ngayDh;
	}

	@Column(name = "TONG_TIEN")
	public Integer getTongTien() {
		return this.tongTien;
	}

	public void setTongTien(Integer tongTien) {
		this.tongTien = tongTien;
	}

	@Column(name = "TEN_KHACHHANG", nullable = false, length = 200)
	public String getTenKhachhang() {
		return this.tenKhachhang;
	}

	public void setTenKhachhang(String tenKhachhang) {
		this.tenKhachhang = tenKhachhang;
	}

	@Column(name = "SDT", length = 20)
	public String getSdt() {
		return this.sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	@Column(name = "DIACHI", length = 500)
	public String getDiachi() {
		return this.diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "donhang")
	public Set getChitietdonhangs() {
		return this.chitietdonhangs;
	}

	public void setChitietdonhangs(Set chitietdonhangs) {
		this.chitietdonhangs = chitietdonhangs;
	}

}

package com.example.demo.entities;
// Generated May 2, 2018 12:42:50 PM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VCommentId generated by hbm2java
 */
@Embeddable
public class VCommentId implements java.io.Serializable {

	private int idCmt;
	private int idSanpham;
	private String tenKh;
	private Date thoiGian;
	private String noiDung;
	private String tenDangnhap;

	public VCommentId() {
	}

	public VCommentId(int idCmt, int idSanpham, Date thoiGian) {
		this.idCmt = idCmt;
		this.idSanpham = idSanpham;
		this.thoiGian = thoiGian;
	}

	public VCommentId(int idCmt, int idSanpham, String tenKh, Date thoiGian, String noiDung, String tenDangnhap) {
		this.idCmt = idCmt;
		this.idSanpham = idSanpham;
		this.tenKh = tenKh;
		this.thoiGian = thoiGian;
		this.noiDung = noiDung;
		this.tenDangnhap = tenDangnhap;
	}

	@Column(name = "ID_CMT", nullable = false)
	public int getIdCmt() {
		return this.idCmt;
	}

	public void setIdCmt(int idCmt) {
		this.idCmt = idCmt;
	}

	@Column(name = "ID_SANPHAM", nullable = false)
	public int getIdSanpham() {
		return this.idSanpham;
	}

	public void setIdSanpham(int idSanpham) {
		this.idSanpham = idSanpham;
	}

	@Column(name = "TEN_KH", length = 200)
	public String getTenKh() {
		return this.tenKh;
	}

	public void setTenKh(String tenKh) {
		this.tenKh = tenKh;
	}

	@Column(name = "THOI_GIAN", nullable = false, length = 19)
	public Date getThoiGian() {
		return this.thoiGian;
	}

	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}

	@Column(name = "NOI_DUNG", length = 500)
	public String getNoiDung() {
		return this.noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	@Column(name = "TEN_DANGNHAP", length = 200)
	public String getTenDangnhap() {
		return this.tenDangnhap;
	}

	public void setTenDangnhap(String tenDangnhap) {
		this.tenDangnhap = tenDangnhap;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VCommentId))
			return false;
		VCommentId castOther = (VCommentId) other;

		return (this.getIdCmt() == castOther.getIdCmt()) && (this.getIdSanpham() == castOther.getIdSanpham())
				&& ((this.getTenKh() == castOther.getTenKh()) || (this.getTenKh() != null
						&& castOther.getTenKh() != null && this.getTenKh().equals(castOther.getTenKh())))
				&& ((this.getThoiGian() == castOther.getThoiGian()) || (this.getThoiGian() != null
						&& castOther.getThoiGian() != null && this.getThoiGian().equals(castOther.getThoiGian())))
				&& ((this.getNoiDung() == castOther.getNoiDung()) || (this.getNoiDung() != null
						&& castOther.getNoiDung() != null && this.getNoiDung().equals(castOther.getNoiDung())))
				&& ((this.getTenDangnhap() == castOther.getTenDangnhap())
						|| (this.getTenDangnhap() != null && castOther.getTenDangnhap() != null
								&& this.getTenDangnhap().equals(castOther.getTenDangnhap())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdCmt();
		result = 37 * result + this.getIdSanpham();
		result = 37 * result + (getTenKh() == null ? 0 : this.getTenKh().hashCode());
		result = 37 * result + (getThoiGian() == null ? 0 : this.getThoiGian().hashCode());
		result = 37 * result + (getNoiDung() == null ? 0 : this.getNoiDung().hashCode());
		result = 37 * result + (getTenDangnhap() == null ? 0 : this.getTenDangnhap().hashCode());
		return result;
	}

}

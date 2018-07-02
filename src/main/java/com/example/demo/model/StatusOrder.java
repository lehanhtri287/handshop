package com.example.demo.model;

public class StatusOrder {
	private int status;
	private String statusContent;

	public StatusOrder(int status, String statusContent) {
		super();
		this.status = status;
		this.statusContent = statusContent;
	}

	public StatusOrder(int status) {
		this.status = status;
		if (status == 0) {
			this.statusContent = "Đang xác nhận";
		} else if (status == 1) {
			this.statusContent = "Xác nhận";
		} else if (status == 2) {
			this.statusContent = "Đã giao hàng";
		} else {
			this.statusContent = "Đã hủy đơn hàng";
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusContent() {
		return statusContent;
	}

	public void setStatusContent(String statusContent) {
		this.statusContent = statusContent;
	}
	
	@Override
	public String toString() {
		return "StatusOrder [status=" + status + ", statusContent=" + statusContent + "]";
	}

}

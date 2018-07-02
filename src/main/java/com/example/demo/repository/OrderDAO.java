package com.example.demo.repository;

import java.util.List;

import com.example.demo.entities.Chitietdonhang;
import com.example.demo.entities.Order;

public interface OrderDAO {
	public List<Order> getListOrders();

	public int getInsertedID();

	public boolean insertOrder(Order order);

	public boolean updateOrder(Order order);

	public boolean deleteOrder(int idOrder);

	public boolean insertOrderDetail(int idDonhang, int idSanpham, int soLuong);

	public int size();

	public List<Order> getNewOrders();

	public List<Order> getOrdersByEmail(String email);

	public List<Chitietdonhang> getOrderDetailById(Integer orderId);

	public Order getOrderById(Integer orderId);

	public boolean cancelOrderById(Integer orderId);

	public List<Order> getOrdersPagination(int page, int pageSize);
	
	public int getNumPages(int pageSize);

	public boolean updateStatusOrder(int idDonhang, int status);
}

package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Chitietdonhang;
import com.example.demo.entities.Order;
import com.example.demo.repository.OrderDAO;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDAO;

	@Override
	public List<Order> getOrders() {
		return orderDAO.getListOrders();
	}

	@Override
	public boolean insertOrder(Order order) {
		return orderDAO.insertOrder(order);
	}

	@Override
	public boolean updateOrder(Order order) {
		return orderDAO.updateOrder(order);
	}

	@Override
	public boolean deleteOrder(int idDonhang) {
		return orderDAO.deleteOrder(idDonhang);
	}

	@Override
	public int getInsertedID() {
		return orderDAO.getInsertedID();
	}

	@Override
	public boolean insertOrderDetail(int idDonhang, int idSanpham, int soLuong) {
		return orderDAO.insertOrderDetail(idDonhang, idSanpham, soLuong);
	}

	@Override
	public int size() {
		return orderDAO.size();
	}

	@Override
	public List<Order> getNewOrders() {
		return orderDAO.getNewOrders();
	}

	@Override
	public List<Order> getOrdersByEmail(String email) {
		return orderDAO.getOrdersByEmail(email);
	}

	@Override
	public List<Chitietdonhang> getOrderDetailById(Integer orderId) {
		return orderDAO.getOrderDetailById(orderId);
	}

	@Override
	public Order getOrderById(Integer orderId) {
		return orderDAO.getOrderById(orderId);
	}

	@Override
	public boolean cancelOrderById(Integer orderId) {
		return orderDAO.cancelOrderById(orderId);
	}

	@Override
	public int getNumPages(int pageSize) {
		return orderDAO.getNumPages(pageSize);
	}

	@Override
	public List<Order> getOrdersPagination(int page, int pageSize) {
		return orderDAO.getOrdersPagination(page, pageSize);
	}

	@Override
	public boolean updateStatusOrder(int idDonhang, int status) {
		// TODO Auto-generated method stub
		return orderDAO.updateStatusOrder(idDonhang, status);
	}

}

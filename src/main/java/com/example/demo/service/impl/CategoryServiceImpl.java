package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.repository.CategoryDAO;
import com.example.demo.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public List<Category> getAllCategories() {
		return categoryDAO.getAllCategory();
	}

	@Override
	public Category getCategory(int idCate) {
		return categoryDAO.getCategory(idCate);
	}

	@Override
	public boolean insertCategory(Category loaihang) {
		return categoryDAO.addCategory(loaihang);
	}

	@Override
	public boolean updateCategory(Category loaihang) {
		return categoryDAO.updateCategory(loaihang);
	}

	@Override
	public boolean deleteCategory(int idCate) {
		return categoryDAO.deleteCategory(idCate);
	}

}

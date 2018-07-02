package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Category;

public interface CategoryService {
	public List<Category> getAllCategories();

	public Category getCategory(int idCate);

	public boolean insertCategory(Category loaihang);

	public boolean updateCategory(Category loaihang);

	public boolean deleteCategory(int idCate);
}

package com.example.demo.repository;

import java.util.List;

import com.example.demo.entities.Category;

public interface CategoryDAO {

	public List<Category> getAllCategory();

	public Category getCategory(int idCate);

	public boolean addCategory(Category loaihang);

	public boolean updateCategory(Category loaihang);

	public boolean deleteCategory(int idLoaihang);
}

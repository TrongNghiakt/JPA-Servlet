package vn.iotstart.dao;

import java.util.List;

import vn.iotstart.entity.Category;

public interface ICategoryDao {

	int count();

	Category findByCategoryname(String catname) throws Exception;

	List<Category> findAll();

	Category findById(int cateid);

	void delete(int cateid) throws Exception;

	void update(Category category);

	void insert(Category category);

	List<Category> findAll(int page, int pagesize);

	public List<Category> searchByName(String catname);

}

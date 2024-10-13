package vn.iotstart.services.impl;

import java.util.List;

import vn.iotstart.dao.ICategoryDao;
import vn.iotstart.dao.impl.CategoryDao;
import vn.iotstart.entity.Category;
import vn.iotstart.services.ICategoryService;

public class CategoryService implements ICategoryService {
	ICategoryDao cateDao = new CategoryDao();

	@Override
	public int count() {
		return cateDao.count();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public Category findByCategoryname(String catname) {
		try {

			return cateDao.findByCategoryname(catname);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public Category findById(int cateid) {
		return cateDao.findById(cateid);
	}

	@Override
	public void delete(int cateid) throws Exception {

		try {

			cateDao.delete(cateid);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public void update(Category category) {
		Category cate = this.findById(category.getCategoryid());

		if (cate != null) {

			cateDao.update(category);

		}

	}

	@Override
	public void insert(Category category) {
		Category cate = this.findByCategoryname(category.getCategoryname());

		if (cate == null) {

			cateDao.insert(category);

		}
	}

	@Override
	public List<Category> searchByName(String catname) {
		return cateDao.searchByName(catname);
	}

}

package vn.iotstart.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstart.configs.JpaConfig;
import vn.iotstart.dao.ICategoryDao;
import vn.iotstart.entity.Category;

public class CategoryDao implements ICategoryDao {
	@Override
	public void insert(Category category) {
		EntityManager emma = JpaConfig.getEntityManager();
		EntityTransaction trans = emma.getTransaction();
		try {
			trans.begin();
			// goi phuong thuc de insert, update, delete
			emma.persist(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			emma.close();
		}

	}

	@Override
	public void update(Category category) {
		EntityManager emma = JpaConfig.getEntityManager();
		EntityTransaction trans = emma.getTransaction();
		try {
			trans.begin();
			// goi phuong thuc de insert, update, delete
			emma.merge(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			emma.close();
		}

	}

	@Override
	public void delete(int cateid) throws Exception {
		EntityManager emma = JpaConfig.getEntityManager();
		EntityTransaction trans = emma.getTransaction();
		try {
			trans.begin();
			// goi phuong thuc de insert, update, delete
			Category category = emma.find(Category.class, cateid);
			if (category != null) {
				emma.remove(category);
			} else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			emma.close();
		}

	}

	@Override
	public Category findById(int cateid) {
		EntityManager emma = JpaConfig.getEntityManager();
		Category category = emma.find(Category.class, cateid);
		return category;
	}

	@Override
	public List<Category> findAll() {
		EntityManager emma = JpaConfig.getEntityManager();
		TypedQuery<Category> query = emma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		EntityManager emma = JpaConfig.getEntityManager();
		TypedQuery<Category> query = emma.createNamedQuery("Category.findAll", Category.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager emma = JpaConfig.getEntityManager();
		String jpql = "Select count(c) from Category c";
		Query query = emma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public List<Category> searchByName(String catname) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c WHERE c.catename like :catname";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("catename", "%" + catname + "%");
		return query.getResultList();
	}

	@Override
	public Category findByCategoryname(String catname) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager();

		String jpql = "SELECT c FROM Category c WHERE c.categoryname =:catename";

		try {

			TypedQuery<Category> query = enma.createQuery(jpql, Category.class);

			query.setParameter("catename", catname);

			Category category = query.getSingleResult();

			if (category == null) {

				throw new Exception("Category Name đã tồn tại");

			}

			return category;

		} finally {

			enma.close();
		}
	}

}

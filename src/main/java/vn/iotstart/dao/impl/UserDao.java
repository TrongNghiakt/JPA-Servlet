package vn.iotstart.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstart.configs.JpaConfig;
import vn.iotstart.dao.IUserDao;
import vn.iotstart.entity.User;

public class UserDao implements IUserDao {

	@Override
	public int count() {
		EntityManager emma = JpaConfig.getEntityManager();
		String jpql = "Select count(c) from User c";
		Query query = emma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public User findByUsername(String uname) {
		EntityManager emma = JpaConfig.getEntityManager();
		User user = emma.find(User.class, uname);
		return user;
	}

	@Override
	public List<User> findAll() {
		EntityManager emma = JpaConfig.getEntityManager();
		TypedQuery<User> query = emma.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public User findUserById(String userid) {
		EntityManager emma = JpaConfig.getEntityManager();
		User user = emma.find(User.class, userid);
		return user;
	}

	@Override
	public void delete(String userid) throws Exception {
		EntityManager emma = JpaConfig.getEntityManager();
		EntityTransaction trans = emma.getTransaction();
		try {
			trans.begin();
			// goi phuong thuc de insert, update, delete
			User user = emma.find(User.class, userid);
			if (user != null) {
				emma.remove(user);
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
	public void update(User user) {
		EntityManager emma = JpaConfig.getEntityManager();
		EntityTransaction trans = emma.getTransaction();
		try {
			trans.begin();
			// goi phuong thuc de insert, update, delete
			emma.merge(user);
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
	public void insert(User user) {
		EntityManager emma = JpaConfig.getEntityManager();
		EntityTransaction trans = emma.getTransaction();
		try {
			trans.begin();
			// goi phuong thuc de insert, update, delete
			emma.persist(user);
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
	public List<User> findAll(int page, int pagesize) {
		EntityManager emma = JpaConfig.getEntityManager();
		TypedQuery<User> query = emma.createNamedQuery("User.findAll", User.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public List<User> searchByName(String uname) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM User c WHERE c.username like :uname";
		TypedQuery<User> query = enma.createQuery(jpql, User.class);
		query.setParameter("username", "%" + uname + "%");
		return query.getResultList();
	}

}

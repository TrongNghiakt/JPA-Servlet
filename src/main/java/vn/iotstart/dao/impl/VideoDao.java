package vn.iotstart.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstart.configs.JpaConfig;
import vn.iotstart.dao.IVideoDao;
import vn.iotstart.entity.Video;

public class VideoDao implements IVideoDao {

	@Override
	public int count() {
		EntityManager emma = JpaConfig.getEntityManager();
		String jpql = "Select count(c) from Video c";
		Query query = emma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public Video findByTitle(String title) {
		EntityManager emma = JpaConfig.getEntityManager();
		Video video = emma.find(Video.class, title);
		return video;
	}

	@Override
	public List<Video> findAll() {
		EntityManager emma = JpaConfig.getEntityManager();
		TypedQuery<Video> query = emma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public Video findVideoById(String videoid) {
		EntityManager emma = JpaConfig.getEntityManager();
		Video video = emma.find(Video.class, videoid);
		return video;
	}

	@Override
	public void delete(String videoid) throws Exception {
		EntityManager emma = JpaConfig.getEntityManager();
		EntityTransaction trans = emma.getTransaction();
		try {
			trans.begin();
			Video video = emma.find(Video.class, videoid);
			if (video != null) {
				emma.remove(video);
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
	public void update(Video video) {
		EntityManager emma = JpaConfig.getEntityManager();
		EntityTransaction trans = emma.getTransaction();
		try {
			trans.begin();
			emma.merge(video);
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
	public void insert(Video video) {
		EntityManager emma = JpaConfig.getEntityManager();
		EntityTransaction trans = emma.getTransaction();
		try {
			trans.begin();
			emma.persist(video);
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
	public List<Video> findAll(int page, int pagesize) {
		EntityManager emma = JpaConfig.getEntityManager();
		TypedQuery<Video> query = emma.createNamedQuery("Video.findAll", Video.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public List<Video> searchByTitle(String title) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Video c WHERE c.title like :title";
		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
		query.setParameter("title", "%" + title + "%");
		return query.getResultList();
	}

}

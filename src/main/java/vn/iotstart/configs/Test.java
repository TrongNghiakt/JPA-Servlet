package vn.iotstart.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstart.entity.Category;
import vn.iotstart.entity.User;
import vn.iotstart.entity.Video;

public class Test {

	public static void main(String[] args) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		Category cate = new Category();
		cate.setCategoryname("Iphone");
		cate.setImages("abc.jpg");
		cate.setStatus(1);
		Video video = new Video();
		video.setVideoid("v002");
		video.setTitle("test");
		video.setCategory(cate);
		User user = new User();
		user.setUserid("1");
		user.setUsername("nghia");
		user.setPassword("123");
		user.setFullname("Nguyen Trong Nghia");
		user.setEmail("nghia7h@gmail.com");
		user.setRole(1);
		try {

			trans.begin();

			/*
			 * enma.persist(cate); enma.persist(video);
			 * 
			 * enma.persist(user);
			 */

			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
}
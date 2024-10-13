package vn.iotstart.services.impl;

import java.util.List;

import vn.iotstart.dao.IUserDao;
import vn.iotstart.dao.impl.UserDao;
import vn.iotstart.entity.User;
import vn.iotstart.services.IUserService;

public class UserService implements IUserService {
	IUserDao userDao = new UserDao();

	@Override
	public int count() {
		return userDao.count();
	}

	@Override
	public User findByUsername(String uname) {
		return userDao.findByUsername(uname);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findUserById(String userid) {
		return userDao.findUserById(userid);
	}

	@Override
	public void delete(String userid) throws Exception {
		try {

			userDao.delete(userid);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public void update(User user) {
		User u = this.findUserById(user.getUserid());
		if (u != null) {
			userDao.update(user);
		}

	}

	@Override
	public void insert(User user) {
		User u = this.findByUsername(user.getUsername());
		if (u == null) {
			userDao.insert(user);
		}

	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		return userDao.findAll(page, pagesize);
	}

	@Override
	public List<User> searchByName(String uname) {
		return userDao.searchByName(uname);
	}

}

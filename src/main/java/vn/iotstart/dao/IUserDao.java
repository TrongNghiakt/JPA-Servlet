package vn.iotstart.dao;

import java.util.List;

import vn.iotstart.entity.User;

public interface IUserDao {
	int count();

	User findByUsername(String uname);

	List<User> findAll();

	User findUserById(String userid);

	void delete(String userid) throws Exception;

	void update(User user);

	void insert(User user);

	List<User> findAll(int page, int pagesize);

	public List<User> searchByName(String uname);
}

package vn.iotstart.services;

import java.util.List;

import vn.iotstart.entity.Video;

public interface IVideoService {
	int count();

	Video findByTitle(String title);

	List<Video> findAll();

	Video findVideoById(String videoid);

	void delete(String videoid) throws Exception;

	void update(Video video);

	void insert(Video video);

	List<Video> findAll(int page, int pagesize);

	public List<Video> searchByTitle(String title);
}

package vn.iotstart.services.impl;

import java.util.List;

import vn.iotstart.dao.IVideoDao;
import vn.iotstart.dao.impl.VideoDao;
import vn.iotstart.entity.Video;
import vn.iotstart.services.IVideoService;

public class VideoService implements IVideoService {

	IVideoDao videodao = new VideoDao();

	@Override
	public int count() {
		return videodao.count();
	}

	@Override
	public Video findByTitle(String title) {
		return videodao.findByTitle(title);
	}

	@Override
	public List<Video> findAll() {
		return videodao.findAll();
	}

	@Override
	public Video findVideoById(String videoid) {
		return videodao.findVideoById(videoid);
	}

	@Override
	public void delete(String videoid) throws Exception {
		try {
			videodao.delete(videoid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Video video) {
		// TODO Auto-generated method stub
		Video v = this.findVideoById(video.getVideoid());
		if (v != null) {
			videodao.update(video);
		}
	}

	@Override
	public void insert(Video video) {
		Video v = this.findVideoById(video.getVideoid());
		if (v == null) {
			videodao.insert(video);
		}

	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return videodao.findAll(page, pagesize);
	}

	@Override
	public List<Video> searchByTitle(String title) {
		return videodao.searchByTitle(title);
	}

}

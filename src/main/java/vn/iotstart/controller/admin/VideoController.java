package vn.iotstart.controller.admin;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstart.dao.impl.VideoDao;
import vn.iotstart.entity.Video;

@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/create", "/admin/video/edit", "/admin/video/update",
		"/admin/video/delete", "/admin/video/reset", "/admin/video/search" })
public class VideoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7210650573278467294L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("delete")) {
			delete(req, resp);
			req.setAttribute("video", new Video());
		} else if (url.contains("edit")) {
			edit(req, resp);
		} else if (url.contains("reset")) {
			req.setAttribute("video", new Video());
		} else if (url.contains("search")) {
			search(req, resp);
			return;
		}
		findAll(req, resp);
		req.getRequestDispatcher("/views/admin/video.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();

		if (url.contains("create")) {
			insert(req, resp);
		} else if (url.contains("delete")) {
			delete(req, resp);
		} else if (url.contains("update")) {
			update(req, resp);
		} else if (url.contains("reset")) {
			req.setAttribute("video", new Video());
		} else if (url.contains("search")) {
			search(req, resp);
			return;
		}
		findAll(req, resp);
		req.getRequestDispatcher("/views/admin/video.jsp").forward(req, resp);
	}

	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			VideoDao dao = new VideoDao();

			List<Video> list = dao.findAll();

			req.setAttribute("videos", list);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Video video = new Video();

			BeanUtils.populate(video, req.getParameterMap());

			VideoDao dao = new VideoDao();
			dao.insert(video);

			req.setAttribute("message", "Video inserted success!");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String videoid = req.getParameter("videoid");
		if (videoid != null && !videoid.isEmpty()) {
			try {
				VideoDao dao = new VideoDao();
				Video video = dao.findVideoById(videoid);
				req.setAttribute("video", video);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "Error: " + e.getMessage());
			}
		}
	}

	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Video video = new Video();

			BeanUtils.populate(video, req.getParameterMap());

			VideoDao dao = new VideoDao();
			dao.update(video);
			req.setAttribute("video", video);
			req.setAttribute("message", "Video updated success!");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String videoId = req.getParameter("videoid");

			VideoDao dao = new VideoDao();
			dao.delete(videoId);

			req.setAttribute("message", "Video deleted success!");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String titleSearch = request.getParameter("title-search");
		VideoDao videodao = new VideoDao();
		if (titleSearch == null || titleSearch.trim().isEmpty()) {
			findAll(request, response);
			request.getRequestDispatcher("/views/admin/video.jsp").forward(request, response);
			return;
		}

		try {
			List<Video> videos = videodao.searchByTitle(titleSearch);
			request.setAttribute("videos", videos);
			request.setAttribute("searchQuery", "");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error during search: " + e.getMessage());
		}

		request.getRequestDispatcher("/views/admin/video.jsp").forward(request, response);
	}

}

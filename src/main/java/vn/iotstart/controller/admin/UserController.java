package vn.iotstart.controller.admin;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstart.dao.impl.UserDao;
import vn.iotstart.entity.User;

@WebServlet(urlPatterns = { "/admin/users", "/admin/user/create", "/admin/user/edit", "/admin/user/update",
		"/admin/user/delete", "/admin/user/reset" })
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3861846090301575253L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		User user = null;
		if (url.contains("delete")) {
			delete(req, resp);
			user = new User();
			req.setAttribute("user", user);
		} else if (url.contains("edit")) {
			edit(req, resp);
		} else if (url.contains("reset")) {
			user = new User();
			req.setAttribute("user", user);
		}
		findAll(req, resp);
		req.getRequestDispatcher("/views/admin/user.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();

		if (url.contains("create")) {
			insert(req, resp);
		} else if (url.contains("delete")) {
			delete(req, resp);
			req.setAttribute("user", new User());
		} else if (url.contains("update")) {
			update(req, resp);
		} else if (url.contains("reset")) {
			req.setAttribute("user", new User());
		}
		findAll(req, resp);
		req.getRequestDispatcher("/views/admin/user.jsp").forward(req, resp);

	}

	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			User user = new User();

			BeanUtils.populate(user, req.getParameterMap());

			UserDao dao = new UserDao();
			dao.insert(user);

			req.setAttribute("message", "User inserted success!");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			UserDao dao = new UserDao();

			List<User> list = dao.findAll();

			req.setAttribute("users", list);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = req.getParameter("userid");
		if (userid != null && !userid.isEmpty()) {
			try {
				UserDao dao = new UserDao();
				User user = dao.findUserById(userid);
				req.setAttribute("user", user);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("error", "Error: " + e.getMessage());
			}
		}
	}

	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			User user = new User();
			BeanUtils.populate(user, req.getParameterMap());

			UserDao dao = new UserDao();
			dao.update(user);

			req.setAttribute("user", user);
			req.setAttribute("message", "User updated success!");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String userId = req.getParameter("userid");

			UserDao dao = new UserDao();
			dao.delete(userId);

			req.setAttribute("message", "User deleted success!");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}
}

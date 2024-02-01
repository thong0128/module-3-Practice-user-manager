package controller;

import model.User;
import service.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        try {
            switch (action) {
                case "create":
                    insertUser(req, resp);
                    break;
                case "edit":
                    updateUser(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        User newUser = new User(name, email, country);
        userDAO.insertUser(newUser);
        resp.sendRedirect("/users");
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/create.jsp");
//        requestDispatcher.forward(req, resp);
}
private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
    int id = Integer.parseInt(req.getParameter("id"));
    String name = req.getParameter("name");
    String email = req.getParameter("email");
    String country = req.getParameter("country");
    User user = new User(id, name, email, country);
    userDAO.updateUser(user);
    resp.sendRedirect("/users?action=edit&id="+id);
//    resp.sendRedirect("/users");
//    RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/edit.jsp");
//    requestDispatcher.forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        try {
            switch (action) {
                case "create":
                    showNewForm(req,resp);
                    break;
                case "edit":
                    showEditForm(req,resp);
                    break;
                case "delete":
                    deleteUser(req,resp);
                    break;
                default:
                    listUser(req,resp);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = userDAO.selectAllUsers();
        req.setAttribute("userList", userList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/list.jsp");
        requestDispatcher.forward(req, resp);
    }
    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user/create.jsp").forward(req, resp);
    }
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        req.setAttribute("user", existingUser);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/edit.jsp");
        requestDispatcher.forward(req, resp);
    }
    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        userDAO.deleteUser(id);

        List<User> userList = userDAO.selectAllUsers();
        req.setAttribute("userList", userList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user/list.jsp");
        requestDispatcher.forward(req, resp);
    }
}

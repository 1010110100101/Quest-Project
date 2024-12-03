package model.services;

import DAL.UserDAO;

import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserManager {

    private static final UserDAO userDAO = new UserDAO();

    public static boolean isUserAlreadyExists(String username) {
        if(username.isEmpty())
            return false;

        return userDAO.getUserByUsername(username) != null;
    }


    public static boolean insertNewUser(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) {
            return false;
        }

        return userDAO.insertUser(new User(0, username, password));
    }

    public static User getUserByLoginAndPassword(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) {
            return null;
        }

        return userDAO.getUserByLoginAndPassword(username, password);
    }

    public static boolean checkAuthorization(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        }
        return true;
    }

    public static User getUserFromSession(HttpServletRequest request) throws IOException {
        return (User) request.getSession().getAttribute("user");
    }
}

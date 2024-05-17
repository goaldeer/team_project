package servlets;

import beans.User;
import dao.UserDAO;
import utils.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        try {
            UserDAO userDAO = new UserDAO(DatabaseConnection.initializeDatabase());
            boolean success = userDAO.registerUser(user);
            if (success) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("register.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp");
        }
    }
}

package servlets;

import beans.Post;
import beans.User;
import dao.PostDAO;
import utils.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Post post = new Post();
        post.setUserId(user.getUserId());
        post.setTitle(title);
        post.setContent(content);

        try {
            PostDAO postDAO = new PostDAO(DatabaseConnection.initializeDatabase());
            postDAO.createPost(post);
            response.sendRedirect("view_posts.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("new_post.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PostDAO postDAO = new PostDAO(DatabaseConnection.initializeDatabase());
            List<Post> posts = postDAO.getAllPosts();
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("view_posts.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp");
        }
    }
}

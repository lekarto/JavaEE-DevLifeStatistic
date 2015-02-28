package ru.devlifestatistic.servlets;

import ru.devlifestatistic.model.Comment;
import ru.devlifestatistic.tools.DLParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CommentsOfEntryServlet", urlPatterns = "/comments")
public class CommentsOfEntryServlet extends HttpServlet {
    private final String URL_PATTERN = "/comments";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/templates/commentsOfEntry.jsp");
        List<Comment> comments = DLParser.getCommentsForEntry(12622);
        request.setAttribute("comments", comments);
        request.setAttribute("urlPattern", URL_PATTERN);
        view.forward(request, response);
    }
}

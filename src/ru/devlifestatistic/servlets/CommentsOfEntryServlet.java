package ru.devlifestatistic.servlets;

import ru.devlifestatistic.model.Comment;
import ru.devlifestatistic.tools.DLParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Mine on 22.02.2015.
 */
public class CommentsOfEntryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/templates/commetsOfEntry.jsp");
        ArrayList<Comment> comments = DLParser.getCommentsForEntry(12622);
        request.setAttribute("comments", comments);
        view.forward(request, response);
    }
}

package ru.devlifestatistic.servlets;

import ru.devlifestatistic.tools.DLParser;

import javax.servlet.RequestDispatcher;
import java.io.IOException;

public class EntriesServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/templates/entries.jsp");

        request.setAttribute("totalEntries", DLParser.getTotalCount());
        request.setAttribute("id", DLParser.getEntry(94).getId());
        request.setAttribute("description", DLParser.getEntry(94).getDescription());
        request.setAttribute("votes", DLParser.getEntry(94).getVotes());
        request.setAttribute("author", DLParser.getEntry(94).getAuthor());
        request.setAttribute("date", DLParser.getEntry(94).getDate());
        request.setAttribute("gifUrl", DLParser.getEntry(94).getGifURL());
        request.setAttribute("imageUrl", DLParser.getEntry(94).getPreviewURL());
        request.setAttribute("type", DLParser.getEntry(94).getType());
        request.setAttribute("embedId", DLParser.getEntry(94).getEmbedId());

        view.forward(request, response);
    }
}

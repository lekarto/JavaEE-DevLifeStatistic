package ru.devlifestatistic.servlets;

import ru.devlifestatistic.dao.implementations.HibernateDaoFactory;
import ru.devlifestatistic.model.Entry;
import ru.devlifestatistic.tools.DLParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EntriesServlet", urlPatterns = "/entries")
public class EntriesServlet extends javax.servlet.http.HttpServlet {
    private final String URL_PATTERN = "/entries";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/templates/entries.jsp");
        Entry entry = DLParser.getEntry(94);
        HibernateDaoFactory.getEntryDao().persist(entry);
        request.setAttribute("entry", entry);
        request.setAttribute("urlPattern", URL_PATTERN);
        view.forward(request, response);
    }
}

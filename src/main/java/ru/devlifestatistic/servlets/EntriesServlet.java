package ru.devlifestatistic.servlets;

import ru.devlifestatistic.model.Entry;
import ru.devlifestatistic.tools.DLParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "EntriesServlet", urlPatterns = "/entries")
public class EntriesServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/templates/entries.jsp");
        Entry entry = DLParser.getEntry(94);
        request.setAttribute("entry", entry);
        view.forward(request, response);
    }
}

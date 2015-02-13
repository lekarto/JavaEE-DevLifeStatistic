package ru.devlifestatistic.servlets;

import ru.devlifestatistic.tools.DLParser;

import javax.servlet.RequestDispatcher;
import java.io.IOException;

public class MainServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/templates/index.jsp");
        request.setAttribute("totalEntries", DLParser.getTotalCount());
        view.forward(request, response);
    }
}

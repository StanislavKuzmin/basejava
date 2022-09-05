package com.urase.webapp.web;

import com.urase.webapp.Config;
import com.urase.webapp.model.Resume;
import com.urase.webapp.storage.SqlStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private final SqlStorage sqlStorage = new SqlStorage(Config.get().getDbUrl(),
            Config.get().getDbUser(), Config.get().getDbPassword());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        List<Resume> resumes = sqlStorage.getAllSorted();
        PrintWriter out = response.getWriter();
        out.println("<table border=\"1\">");
        out.println("<h2>A list of resumes</h2>");
        out.println("<tr>");
        out.println("<th>uuid</th>");
        out.println("<th>full_name</th>");
        out.println("</tr>");
        for (Resume r: resumes) {
            out.println("<tr>");
            out.println("<td>" + r.getUuid() + "</td>");
            out.println("<td>" + r.getFullName() + "</td>");
            out.println("</tr>");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

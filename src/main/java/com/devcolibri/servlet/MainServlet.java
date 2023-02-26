package com.devcolibri.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DirectoryWorker dw = new DirectoryWorker();
        String path = req.getParameter("path");
        List<FileModel> content;
        if(path == null)
            content = new ArrayList<>();
        else content = dw.getList(path);
        req.setAttribute("content", content);
        req.setAttribute("path", path);
        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }
}
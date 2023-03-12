package com.devcolibri.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DirectoryWorker dw = new DirectoryWorker();
        String path;
        String uri = req.getQueryString();
        if (uri != null && uri.contains("path")) {
            path = req.getParameter("path");
        } else {
            path = "/";
        }
        if (path.matches("[A-Z]:")) {
            path = File.listRoots()[0].getPath();
        }
        String absolutePath = new File(path).getAbsolutePath();
        List<FileModel> content;
        content = dw.getList(path);

        req.setAttribute("content", content);
        req.setAttribute("path", absolutePath.replace("\\", "/"));
        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }
}
package com.devcolibri.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DirectoryWorker direct = new DirectoryWorker();
        String path = request.getParameter("path");
        if (path.equals("")){
            path = "/";
        }
        String absolutePath = new File(path).getAbsolutePath();
        List<FileModel> content;
        content = direct.getList(path);
        request.setAttribute("content", content);
        request.setAttribute("path", absolutePath.replace("\\", "/"));
        request.getRequestDispatcher("mypage.jsp").forward(request, response);
    }
}
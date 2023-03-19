package com.devcolibri.servlet;

import model.DirectoryWorker;
import model.FileModel;
import model.UserModel;
import service.AccountService;

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
        if(!AccountService.isSomeoneLogin()){
            response.sendRedirect("http://localhost:8080/login");
        }
        else{
            DirectoryWorker dw = new DirectoryWorker();
            String path;
            String uri = request.getQueryString();
            if (uri != null && uri.contains("path")) {
                path = request.getParameter("path");
            } else {
                path = "/";
            }

            AccountService accountService = new AccountService();
            UserModel user = accountService.getBySession(request.getSession().getId());
            if (accountService.hasActiveSession() || accountService.getBySession(request.getSession().getId()) == null) {
                response.sendRedirect("http://localhost:8080/login");
            }
            if (!path.contains(user.getLogin())) {
                path = user.getHomeDirectory();
                response.sendRedirect("http://localhost:8080/?path=" + path);
            }
            String absolutePath = new File(path).getCanonicalPath();
            List<FileModel> content;
            if(path.contains("\\..")){
                path = path.replaceAll("\\..", "");
            }
            if(path.contains("/..")){
                path = path.replaceAll("/..", "");
            }
            content = dw.getList(path);

            if(path == null) {
                content = new ArrayList<>();
            }

            path = path.replace("/", "\\");
            request.setAttribute("content", content);
            request.setAttribute("path", path);
            request.getRequestDispatcher("mypage.jsp").forward(request, response);
        }
    }
}
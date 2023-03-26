package com.devcolibri.servlet;

import model.UserModel;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    AccountService accountService = new AccountService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        //String email = req.getParameter("email");
        UserModel user = new UserModel(login, password);
        if (accountService.checkUser(login, password)) {
            if (accountService.getBySession(req.getSession().getId()) != null){
                req.setAttribute("error", "user already in system");
                resp.sendRedirect("http://localhost:8080/login");
            } else {
                accountService.addSession(user, req.getSession());
                redirect(user, req, resp);
            }
        } else {
            req.setAttribute("error", "something went wrong");
            resp.sendRedirect("http://localhost:8080/login");
        }
    }

    private void redirect(UserModel user, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("http://localhost:8080/ "+ "?path=" + user.getHomeDirectory());
    }
}

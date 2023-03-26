package com.devcolibri.servlet;

import service.AccountService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    AccountService accountService = new AccountService();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        accountService.removeSession(accountService.getBySession(req.getSession().getId()));
        resp.sendRedirect("http://localhost:8080/login");
    }
}

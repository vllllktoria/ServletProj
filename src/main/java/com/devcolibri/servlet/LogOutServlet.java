package com.devcolibri.servlet;

import model.DirectoryWorker;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountService.logOut(AccountService.getById(request.getSession().getId()));
        AccountService.setSomeoneLogin(false);
        response.sendRedirect("http://localhost:8080/login");
    }
}

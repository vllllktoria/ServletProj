package com.devcolibri.servlet;

import model.DirectoryWorker;
import model.UserModel;
import service.AccountService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;

@WebServlet("/login")
public class RegistrationServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(Objects.equals(request.getParameter("act"), "registration"))
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        else request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String act = request.getParameter("act");
        String login = request.getParameter("login");
        String pas = request.getParameter("password");
        UserModel user = new UserModel(login, pas);
        if(Objects.equals(act, "registration")){
            if(AccountService.checkUser(user)){
                request.setAttribute("error", "User already exists.");
                response.sendRedirect("http://localhost:8080/login?act=registration");
            }else {
                AccountService.addUsers(user, request.getSession());
                AccountService.addSession(user, request.getSession());
                redirect(user, request, response);
            }
        } else {
            if(AccountService.checkUser(user)){
                if (AccountService.getById(request.getSession().getId()) != null) {
                    request.setAttribute("error", "User already exists.");
                    response.sendRedirect("http://localhost:8080/login");
                }
                else {
                    AccountService.addSession(user, request.getSession());
                    redirect(user, request, response);
                }
            } else {
                request.setAttribute("error", "Please, try again.");
                response.sendRedirect("http://localhost:8080/login");
            }
        }
    }

    private void redirect(UserModel user, HttpServletRequest r, HttpServletResponse re) throws IOException{
        AccountService.setSomeoneLogin(true);
        re.sendRedirect("http://localhost:8080/?path=" + user.getHomeDirectory());
    }

}

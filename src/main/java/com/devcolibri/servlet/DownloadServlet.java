package com.devcolibri.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/download/")
public class DownloadServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path;
        if (request.getQueryString().contains("path")){
            path = request.getParameter("path");
        }else{
            path = "/";
        }
        path = path.replace('/', '\\');
        File file = new File(path);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        try (FileInputStream inputStream = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            byte[] bytes = new byte[4096];
            int length;
            while ((length = inputStream.read(bytes)) > 0)
                out.write(bytes, 0, length);
        }
    }
}
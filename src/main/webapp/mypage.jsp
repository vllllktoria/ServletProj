<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="com.devcolibri.servlet.FileModel" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<%= new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()) %>
<h1> Directory: ${path} </h1>
    <a style="text-decoration: none;" href="./?path=<%=request.getAttribute("path").toString()%>/..">Up</a>
    <br>
    <br>
    <table>
        <tr>
            <th></th>
            <th>Файл</th>
            <th>Размер</th>
            <th>Дата</th>
        </tr>
        <%
            ArrayList<FileModel> list = (ArrayList<FileModel>)request.getAttribute("content");
            for(FileModel e: list)
            {
                if(e.isDirectory()){
        %>
        <tr>
            <td><a style="text-decoration: none;" href="./?path=<%=request.getAttribute("path")%>/<%=e.getName()%>"><%=e.getName()%>/</a></td>
            <td></td>
            <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy, hh:mm:ss a").format(new Date(e.getLastModified())) %></td>
        </tr>

        <%}else{%>

        <tr>
            <td><a style="text-decoration: none;" href="http://localhost:8080/download/?path=<%=request.getAttribute("path")%>/<%=e.getName()%>"><%=e.getName()%></a></td>
            <td><%=e.getLength()%> B</td>
            <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy, hh:mm:ss a").format(new Date(e.getLastModified())) %></td>
        </tr>
        <%}
        }%>
    </table>
</body>
</html>
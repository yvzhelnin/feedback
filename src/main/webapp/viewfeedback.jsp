<%@page contentType="text/html" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="yz.feedback.*"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Полученная обратная связь</title>
        <style>
        table {
         border-spacing: 40px 5px;
        }
        td {
         padding: 5px;
        }
  </style>
    </head>
    <body>
        <a href="index.jsp"><h5>К форме обратной связи</h5></a>
        <table>
            <tr>
                <td><h4>ФИО Отправителя</h4></td>
                <td><h4>Получатель</h4></td>
                <td><h4>Тема сообщения</h4></td>
                <td><h4>Сообщение</h4></td>
            </tr>
            <%  Iterator<Feedback> iterator = DataBaseWorker.getFeedback().iterator();
                while (iterator.hasNext()) {
                    out.println(iterator.next());
                }
            %>
        </table>
    </body>
</html>

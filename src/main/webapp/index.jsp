<%@page contentType="text/html" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="yz.feedback.*"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html><html>
    <head>
        <title>Форма обратной связи</title>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    </head>
    <body>
        <form id="feedbackform">
        <h5>Форма обратной связи</h5>
        <input type="text" required="" placeholder="Фамилия" name="secondName">
        <input type="text" required="" placeholder="Имя" name="firstName">
        <input type="text" placeholder="Отчество" name="patronymic"><br/><br/>
        <select required size="1" name="recipient">
            <option selected disabled value=''>Выберите получателя</option>
            <%  Iterator<Recipient> iterator = DataBaseWorker.getRecipientName().iterator();
                while (iterator.hasNext()) {
                    out.println(iterator.next());
                }
            %>
        </select><br/><br/>
        <input type="text" placeholder="Тема" name="topic"><br/><br/>
        <textarea name="message" required="" placeholder="Текст сообщения" rows="10" cols="30"></textarea><br/>
        <input type="submit" id="button" value="Отправить" name="sendfeedback">
        </form>
        <div id="result"></div><br/>
        <a href="viewfeedback.jsp"><h5>Просмотреть отправленные сообщения</h5></a>
        <script type="text/javascript">
        $("#feedbackform").submit(function(event) {
            event.preventDefault();
            $.ajax({
                type: "POST",
                url: "send",
                data: $(this).serialize(),
                success: function() {
                    var div = document.createElement("div");
                    div.innerHTML = "<h5>Обратная связь успешно отправлена!</h5>";
                    document.getElementById("result").appendChild(div);
                    document.getElementById('feedbackform').reset();
               }
            });
        });
        </script>
    </body>
</html>

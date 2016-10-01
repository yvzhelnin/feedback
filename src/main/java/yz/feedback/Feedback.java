package yz.feedback;

//класс сообщений
public class Feedback {
    //переменные, соответствующие тем, что нам нужно выводить на viewfeedback.jsp
    private String fullName;
    private int recipientId;
    private String topic;
    private String message;
    private String recipientFullName;
    private String recipientEmail;

    //пустой конструктор для создания пустых экземпляров
    public Feedback() {
    }
    //геттеры-сеттеры
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getFeedbackRecipientId() {
        return recipientId;
    }

    public void setFeedbackRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }
    
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipientFullName() {
        return recipientFullName;
    }

    public void setRecipientFullName(String recipientFullName) {
        this.recipientFullName = recipientFullName;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }
    
    //переопределяем toString() для вывода в HTML-формате в таблицу на viewfeedback.jsp
    @Override
    public String toString(){
        return "<tr><td>" + fullName + "</td><td>" + recipientFullName +
                "</td><td>" +recipientEmail + "</td><td>" + topic +
                "</td><td>" + message + "</td></tr>";
    }
}

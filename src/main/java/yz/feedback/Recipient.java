package yz.feedback;

//класс получателей
public class Recipient {
    
    //поля класса
    private int recipientId;
    private String recipientFullName;
    private String recipientEmail;

    //пустой конструктор для создания пустых экземпляров
    public Recipient() {
    }

    //геттеры-сеттеры
    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
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
    
    //переопределяем toString для вывода в <select> на index.jsp
    @Override
    public String toString(){
        return "<option value=\"" + recipientId + "\">" + recipientFullName + "</option>";
    }
}

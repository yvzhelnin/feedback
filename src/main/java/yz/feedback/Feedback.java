package yz.feedback;

public class Feedback {
    
    private String fullName;
    private int recipientId;
    private String topic;
    private String message;
    private String recipientFullName;
    private String recipientEmail;

    public Feedback() {
    }

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
    
    @Override
    public String toString(){
        return "<tr><td>" + fullName + "</td><td>" + recipientFullName +
                "</td><td>" +recipientEmail + "</td><td>" + topic +
                "</td><td>" + message + "</td></tr>";
    }
}

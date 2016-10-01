package yz.feedback;

public class Feedback {
    
    private String fullName;
    private String recipient;
    private String topic;
    private String message;

    public Feedback() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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
    
    @Override
    public String toString(){
        return "<tr><td>" + fullName + "</td><td>" + recipient + "</td><td>" +
                topic + "</td><td>"+ message + "</td></tr>";
    }
}

package yz.feedback;

public class Recipient {
    
    private int recipientId;
    private String recipientFullName;
    private String recipientEmail;

    public Recipient() {
    }

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
    
    @Override
    public String toString(){
        return "<option value=\"" + recipientId + "\">" + recipientFullName + "</option>";
    }
}

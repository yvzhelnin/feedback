package yz.feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

public class DataBaseWorker {
    
    private static Connection getConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Feedback", "feedbackadmin", "12345");
        }catch(Exception se){
            logData("Could not connect to the DB + se", "error");
        }
        return null;
    }
    
    public static void tableCreate(){
        
        try{
            Connection con = getConnection();
            
            Statement st = con.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS FEEDBACKS("
                + "FEEDBACK_ID BIGSERIAL,"
                + "FULL_NAME VARCHAR,"
                + "FEEDBACK_RECIPIENT_ID INT,"
                + "TOPIC VARCHAR,"
                + "MESSAGE VARCHAR"
                + ");";
            st.execute(createTableSQL);
            st.close();
            con.close();
        }catch(Exception se){
            logData("Could not create new table FEEDBACKS cause the next exception" + se, "error");
        }
    }
    public static void bindFeedback(String secondName, String firstName,
                        String patronymic, int recipientId,
                        String topic, String message){

        
        try{
            Connection con = getConnection();
            Statement st = con.createStatement();

            String InsertSQL = "INSERT INTO FEEDBACKS (FULL_NAME,FEEDBACK_RECIPIENT_ID,TOPIC,MESSAGE) values('"
                + secondName + " " + firstName + " " + patronymic + "','" + recipientId + "','" + topic + "','" + message + "');";
            
            st.executeUpdate(InsertSQL);
            logData("Added a new entry into the table FEEDBACKS: " + InsertSQL, "info");
            st.close();
            con.close();
        }catch(Exception se){
            logData("Could not write data to table FEEDBACKS cause the next exception" + se, "error");
        }
    }
    public static ArrayList<Feedback> getFeedback(){
        
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        
        try{
            Connection con = getConnection();
            Statement st = con.createStatement();
            String getData = "SELECT full_name,topic,message,recipient_full_name,recipient_email FROM feedbacks LEFT OUTER JOIN recipients ON (feedbacks.feedback_recipient_id=recipients.recipient_id);";

            ResultSet resultSet = st.executeQuery(getData);
            
            while(resultSet.next()){
                Feedback feedback = new Feedback();
                feedback.setFullName(resultSet.getString("FULL_NAME"));
                feedback.setRecipientFullName(resultSet.getString("RECIPIENT_FULL_NAME"));
                feedback.setRecipientEmail(resultSet.getString("RECIPIENT_EMAIL"));
                feedback.setTopic(resultSet.getString("TOPIC"));
                feedback.setMessage(resultSet.getString("MESSAGE"));
                feedbackList.add(feedback);
                logData("A request for a message from " + resultSet.getString("RECIPIENT_FULL_NAME") + " has been executed successfully", "info");
            }
            st.close();
            con.close();
            return feedbackList;
        }catch(Exception se){
            logData("Could not execute SELECT for the message list output" + se, "error");
        }
        return feedbackList;
    }
    
    public static ArrayList<Recipient> getRecipientName(){
        
        ArrayList<Recipient> recipientList = new ArrayList<>();
        
        try{
            Connection con = getConnection();
            Statement st = con.createStatement();
            String getData = "SELECT RECIPIENT_ID,RECIPIENT_FULL_NAME FROM RECIPIENTS";
            
            ResultSet resultSet = st.executeQuery(getData);
            
            while(resultSet.next()){
                Recipient recipient = new Recipient();
                recipient.setRecipientId(resultSet.getInt("RECIPIENT_ID"));
                recipient.setRecipientFullName(resultSet.getString("RECIPIENT_FULL_NAME"));
                recipientList.add(recipient);
                logData("The recipient " + resultSet.getString("RECIPIENT_FULL_NAME") + " has been displayed on the start page", "info");
            }
            st.close();
            con.close();
            return recipientList;
        }catch(Exception se){
            logData("Could not print the recipients list on the start page" + se, "error");
        }
        return recipientList;
    }
    
    private static void logData(String logStr, String stateStr){
        Logger logger = Logger.getLogger(DataBaseWorker.class.getPackage().getName());
        if(stateStr.equals("error")){
            logger.log(Level.SEVERE, logStr);
        }
        if(stateStr.equals("info")){
            logger.info(logStr);
        }
    }
}
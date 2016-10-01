package yz.feedback;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseWorker {
    
    private static Connection getConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Feedback", "feedbackadmin", "12345");
        }catch(Exception se){}
        return null;
    }
    
    public static void tableCreate(){
        try{
            Connection con = getConnection();
            DatabaseMetaData metaData = con.getMetaData();
            ResultSet res = metaData.getTables(null, null, "FEEDBACK_MESSAGES", 
                new String[]{"TABLE"});
            
            if (!res.next()){
                Statement st = con.createStatement();
                String createTableSQL = "CREATE TABLE FEEDBACKS("
                + "FEEDBACK_ID BIGSERIAL,"
                + "FULL_NAME VARCHAR,"
                + "FEEDBACK_RECIPIENT_ID INT,"
                + "TOPIC VARCHAR,"
                + "MESSAGE VARCHAR"
                + ");";
                st.execute(createTableSQL);
                System.out.println("Created!");
                st.close();
            }
            con.close();
        }catch(Exception se){}
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
            st.close();
            con.close();
        }catch(Exception se){}
    }
    public static ArrayList<Feedback> getFeedback(){
        
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        
        try{
            Connection con = getConnection();
            Statement st = con.createStatement();
            String getData = "SELECT FULL_NAME,FEEDBACK_RECIPIENT_ID,TOPIC,MESSAGE FROM FEEDBACKS";
            
            ResultSet resultSet = st.executeQuery(getData);
            
            while(resultSet.next()){
                Feedback feedback = new Feedback();
                feedback.setFullName(resultSet.getString("FULL_NAME"));
                feedback.setFeedbackRecipientId(resultSet.getInt("FEEDBACK_RECIPIENT_ID"));
                feedback.setTopic(resultSet.getString("TOPIC"));
                feedback.setMessage(resultSet.getString("MESSAGE"));
                feedbackList.add(feedback);
            }
            st.close();
            con.close();
            return feedbackList;
        }catch(Exception se){}
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
            }
            st.close();
            con.close();
            return recipientList;
        }catch(Exception se){}
        return recipientList;
    }
}
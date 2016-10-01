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
                + "SECOND_NAME VARCHAR,"
                + "FIRST_NAME VARCHAR,"
                + "PATRONYMIC VARCHAR,"
                + "RECIPIENT VARCHAR,"
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
    public static void bindData(String secondName, String firstName,
                        String patronymic, String recipient,
                        String topic, String message){
        try{
            Connection con = getConnection();
            Statement st = con.createStatement();

            String InsertSQL = "INSERT INTO FEEDBACKS (SECOND_NAME,FIRST_NAME,PATRONYMIC,RECIPIENT,TOPIC,MESSAGE) values('"
                + secondName + "','" + firstName + "','" + patronymic + "','" + recipient + "','" + topic + "','" + message + "');";
            
            st.executeUpdate(InsertSQL);
            st.close();
            con.close();
        }catch(Exception se){}
    }
    public static ArrayList<Feedback> getData(){
        
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        
        try{
            Connection con = getConnection();
            Statement st = con.createStatement();
            String getData = "SELECT SECOND_NAME,FIRST_NAME,PATRONYMIC,RECIPIENT,TOPIC,MESSAGE FROM FEEDBACKS";
            
            ResultSet resultSet = st.executeQuery(getData);
            
            while(resultSet.next()){
                Feedback feedback = new Feedback();
                feedback.setSecondName(resultSet.getString("SECOND_NAME"));
                feedback.setFirstName(resultSet.getString("FIRST_NAME"));
                feedback.setPatronymic(resultSet.getString("PATRONYMIC"));
                feedback.setRecipient(resultSet.getString("RECIPIENT"));
                feedback.setTopic(resultSet.getString("TOPIC"));
                feedback.setMessage(resultSet.getString("MESSAGE"));
                feedbackList.add(feedback);
                
       //         System.out.println(feedback.toString());
            }
            st.close();
            con.close();
            feedbackList.forEach(System.out::println);
            return feedbackList;
        }catch(Exception se){}
        return feedbackList;
    }
}
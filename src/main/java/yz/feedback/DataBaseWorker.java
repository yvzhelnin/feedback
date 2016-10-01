package yz.feedback;

import java.io.IOException;
import java.sql.*;

public class DataBaseWorker {
    
    public static void tableCreate(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Feedback", "feedbackadmin", "12345");
            if(con != null)System.out.println("Connected for creating!");
            
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
}
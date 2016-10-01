package yz.feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

//класс для работы с БД через JDBC драйвер
public class DataBaseWorker {
    
    //метод, устанавливающий соединение с базой
    private static Connection getConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Feedback", "feedbackadmin", "12345");
        }catch(Exception se){
            logData("Could not connect to the DB" + se, "error");
        }
        return null;
    }
    //метод, создающий таблицу сообщений
    public static void tableCreate(){
        
        try{
            //устанавливаем соединение
            Connection con = getConnection();
            //получаем состояние
            Statement st = con.createStatement();
            /*создаём ссылку на запрос на создание таблицы. Проверка на 
            сущестсование выполняется средствами SQL*/
            String createTableSQL = "CREATE TABLE IF NOT EXISTS FEEDBACKS("
                + "FEEDBACK_ID BIGSERIAL,"
                + "FULL_NAME VARCHAR,"
                + "FEEDBACK_RECIPIENT_ID INT,"
                + "TOPIC VARCHAR,"
                + "MESSAGE VARCHAR"
                + ");";
            //выполняем запрос
            st.execute(createTableSQL);
            //закрываем состояние и соединение
            st.close();
            con.close();
        }catch(Exception se){
            logData("Could not create new table FEEDBACKS cause the next exception" + se, "error");
        }
    }
    //метод записывает данные, полученный из формы, в таблицу FEEDBACKS
    public static void bindFeedback(String secondName, String firstName,
                        String patronymic, int recipientId,
                        String topic, String message){

        try{
            //устанавливаем соединение, получаем состояние
            Connection con = getConnection();
            Statement st = con.createStatement();
            //создаём ссылку на запрос, объединяем ФИО
            String InsertSQL = "INSERT INTO FEEDBACKS (FULL_NAME,FEEDBACK_RECIPIENT_ID,TOPIC,MESSAGE) values('"
                + secondName + " " + firstName + " " + patronymic + "','" + recipientId + "','" + topic + "','" + message + "');";
            //выполняем запись
            st.executeUpdate(InsertSQL);
            logData("Added a new entry into the table FEEDBACKS: " + InsertSQL, "info");
            //закрываем состояние и соединение
            st.close();
            con.close();
        }catch(Exception se){
            logData("Could not write data to table FEEDBACKS cause the next exception" + se, "error");
        }
    }
    
    //запрос для вывода существующих сообщений
    public static ArrayList<Feedback> getFeedback(){
        //создаём коллекцию, в который будут храниться экземпляры класса сообщений
        ArrayList<Feedback> feedbackList = new ArrayList<>();
        
        try{
            //устанавливаем соединение, получаем состояние
            Connection con = getConnection();
            Statement st = con.createStatement();
            /*Поскольку необходимо вывести не только данные из таблицы сообщений,
            но и по ID получателя вывести его ФИО и email, пишем SELECT с LEFT OUTER JOIN из 2 таблиц*/
            String getData = "SELECT full_name,topic,message,recipient_full_name,recipient_email FROM feedbacks LEFT OUTER JOIN recipients ON (feedbacks.feedback_recipient_id=recipients.recipient_id);";

            //выполняем запрос, кладём полученные данные в новый ResultSer
            ResultSet resultSet = st.executeQuery(getData);
            
            //в цикле по ResultSet
            while(resultSet.next()){
                //создаём пустой экземпляр FeedBack()
                Feedback feedback = new Feedback();
                //и присваиваем его полям значения колонок
                feedback.setFullName(resultSet.getString("FULL_NAME"));
                feedback.setRecipientFullName(resultSet.getString("RECIPIENT_FULL_NAME"));
                feedback.setRecipientEmail(resultSet.getString("RECIPIENT_EMAIL"));
                feedback.setTopic(resultSet.getString("TOPIC"));
                feedback.setMessage(resultSet.getString("MESSAGE"));
                //добавляем заполенный экземпляр в коллекцию
                feedbackList.add(feedback);
                logData("A request for a message from " + resultSet.getString("RECIPIENT_FULL_NAME") + " has been executed successfully", "info");
            }
            //закрываем состояние и соединение
            st.close();
            con.close();
            //возвращаем коллекцию
            return feedbackList;
        }catch(Exception se){
            logData("Could not execute SELECT for the message list output" + se, "error");
        }
        return feedbackList;
    }
    /*метод для получение имени и ID получателя (используется для вставки в
    <select> на index.jsp)*/
    public static ArrayList<Recipient> getRecipientName(){
        //создаём коллекцию, в который будут храниться экземпляры класса получателей
        ArrayList<Recipient> recipientList = new ArrayList<>();
        
        try{
            //устанавливаем соединение, получаем состояние
            Connection con = getConnection();
            Statement st = con.createStatement();
            //создаём ссылку на запрос
            String getData = "SELECT RECIPIENT_ID,RECIPIENT_FULL_NAME FROM RECIPIENTS";
            //выполняем запрос, результат скоадываем в новый ResultSet
            ResultSet resultSet = st.executeQuery(getData);
            
            //в цикле по ResultSet
            while(resultSet.next()){
                //создаём новый экземпляр Recipient()
                Recipient recipient = new Recipient();
                //и присваиваем его полям значения колонок
                recipient.setRecipientId(resultSet.getInt("RECIPIENT_ID"));
                recipient.setRecipientFullName(resultSet.getString("RECIPIENT_FULL_NAME"));
                recipientList.add(recipient);
                logData("The recipient " + resultSet.getString("RECIPIENT_FULL_NAME") + " has been displayed on the start page", "info");
            }
            //закрываем состояние и соединение
            st.close();
            con.close();
            //возвращаем коллекцию
            return recipientList;
        }catch(Exception se){
            logData("Could not print the recipients list on the start page" + se, "error");
        }
        return recipientList;
    }
    //метод для логирования
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
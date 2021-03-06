package com.example.voting;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class DBUtils {

    private static final String TAG = "DBUtils";

    /**
     * Connect to the database on the server
     * @return return a variable of type Connection
     */
    public static Connection getConn()  {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://rm-p0wmlyb0o7qa4z6n3do.mysql.australia.rds.aliyuncs.com:3306/users", "qchris","GolfR930214");
                return conn;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * Determine if an user can log in (the correctness and consistency of the username and the password)
     * @param username  the username
     * @param password  the password
     * @return a boolean variable, if it is true, the user can log in
     */
    public static boolean loginJudge(String username, String password){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        try {
            conn = DBUtils.getConn();

            String sql = "select count(*) from information where username =? and password=?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int count = rs.getInt(1);

                if(count>0){
                    Log.d(TAG,"success");
                    return true;
                }else{
                    Log.d(TAG,"failed");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Register a new user
     * @param username  user's username
     * @param password  user's password
     * @return a boolean variable that indicates whether or not the registration is successful
     */
    public  static boolean register(String username, String password){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        try {
            conn = DBUtils.getConn();
            String sql = "insert into information(username, password) values(?,?)";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            int result = ps.executeUpdate();
            return result == 1;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get user information from the database based on the user name
     * @param username The user name corresponding to the information to be queried
     * @return an ArrayList containing user information
     */
    public static ArrayList getInformation(String username){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        ArrayList<String> userinfo = new ArrayList<>();
        try {
            conn = DBUtils.getConn();

            String sql = "select age,email,gender from information where username =?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              int age = rs.getInt(1);
              String email = rs.getString(2);
              String gender = rs.getString(3);
              userinfo.add(Integer.toString(age));
              userinfo.add(email);
              userinfo.add(gender);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userinfo;
    }

    /**
     * Pass voting information to the database bases on what user created
     * @param list an ArrayList that contains the information of a created voting
     * @return whether the creation is successful
     */
    public static boolean submitVote(ArrayList<String> list){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String sql = "";
        try {
            conn = DBUtils.getConn();

            switch (list.size()){
                case 4:
                    sql = "insert into voting(title,c_1,c_2,due) values(?,?,?,?)";
                    break;
                case 5:
                    sql = "insert into voting(title,c_1,c_2,c_3,due) values(?,?,?,?,?)";
                    break;
                case 6:
                    sql = "insert into voting(title,c_1,c_2,c_3,c_4,due) values(?,?,?,?,?,?)";
                    break;
                case 7:
                    sql = "insert into voting(title,c_1,c_2,c_3,c_4,c_5,due) values(?,?,?,?,?,?,?)";
                    break;
                default:
                    break;
            }
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
                String info = list.get(i);
                ps.setString(i+1,info);
            }
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Get all voting topics
     * @return an ArrayList contains all topics
     */
    public static ArrayList<String> getTitleInfo(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;

        ArrayList<String> title = new ArrayList<>();
        try {
            conn = DBUtils.getConn();

            String sql = "select title from voting";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               String tit = rs.getString(1);
               title.add(tit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }

    /**
     * Get voting deadlines and topics
     * @return an ArrayList<String[]> contains voting topics and their corresponding deadlines
     */
    public static ArrayList<String[]> getTitleDueInfo(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;

        ArrayList<String[]> titleDue = new ArrayList<>();
        try {
            conn = DBUtils.getConn();

            String sql = "select title,due from voting";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                titleDue.add(new String[]{rs.getString(1),rs.getString(2)});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titleDue;
    }

    /**
     * Get the deadline by the title of a voting
     * @param title a voting title
     * @return a string of a deadline date
     */
    public static String getDueInfo(String title){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String dueDate = "";
        try {
            conn = DBUtils.getConn();
            String sql = "select due from voting where title=?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,title);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                dueDate = rs.getString(1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dueDate;
    }

    /**
     * Get the options of a voting
     * @param title a voting title used to get the option
     * @return an ArrayList<String> contains all options of a voting
     */
    public static ArrayList<String> getChoice(String title){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;

        ArrayList<String> choice = new ArrayList<>();
        try {
            conn = DBUtils.getConn();

            String sql = "select c_1,c_2,c_3,c_4,c_5 from voting where title=?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,title);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                choice.add(rs.getString(1));
                choice.add(rs.getString(2));
                choice.add(rs.getString(3));
                choice.add(rs.getString(4));
                choice.add(rs.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choice;
    }

    /**
     * Update the data in the database based on the poll results.
     * @param title the voting title
     * @param id option that needs to be updated
     * @return whether the update is successful
     */
    public static boolean count(String title, String id){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;

        try {
            conn = DBUtils.getConn();
            String sql = "update voting set "+"c_"+id+"_c " + "= c_"+id+"_c + 1 where title = ?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,title);
            ps.executeUpdate();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get the result of voting according to the title
     * @param title the voting title
     * @return an ArrayList<String[]> contains all options and their corresponding results
     */
    public static ArrayList<String[]> countResult(String title){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        ArrayList<String[]> countResult = new ArrayList<String[]>();
        try {
            conn = DBUtils.getConn();

            String sql = "select * from voting where title=?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString(2) != null){
                    countResult.add(new String[]{rs.getString(2), Integer.toString(rs.getInt(3))});
                }
                if(rs.getString(4) != null){
                    countResult.add(new String[]{rs.getString(4), Integer.toString(rs.getInt(5))});
                }
                if(rs.getString(6) != null){
                    countResult.add(new String[]{rs.getString(6), Integer.toString(rs.getInt(7))});
                }
                if(rs.getString(8) != null){
                    countResult.add(new String[]{rs.getString(8), Integer.toString(rs.getInt(9))});
                }
                if(rs.getString(10) != null){
                    countResult.add(new String[]{rs.getString(10), Integer.toString(rs.getInt(11))});
                }

                return countResult;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  Store the survey onto the server
     * @param my_survey
     * @param que_choice_pair
     */
    public static void StoreSurveyData (ArrayList<String[]> my_survey, ArrayList<ArrayList<String>> que_choice_pair){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;

        ArrayList<String> qc;


        try {
            conn = DBUtils.getConn();

            assert conn != null;
            for (int i = 0; i < my_survey.size(); i++){
                String [] info = my_survey.get(i);
                String question = info[4];
                System.out.println(question);
                String type = info[5];
                String sql2 = "insert into survey(username, survey_title, due_date, question, question_type, choice_num, limited_num) values(?,?,?,?,?,?,?)";
                PreparedStatement ps_insert2 = conn.prepareStatement(sql2);

                ps_insert2.setString(1, info[0]);
                ps_insert2.setString(2, info[1]);
                ps_insert2.setString(3, info[2]);
                ps_insert2.setString(4, info[3]);
                ps_insert2.setString(5, info[4]);
                ps_insert2.setString(6, info[5]);
                ps_insert2.setString(7, info[6]);
                ps_insert2.executeUpdate();

                String sql_create = "create table " + question +"(que_title varchar(100), choice varchar(100));";
                String sql1 = "insert into " + question + "(que_title, choice) values(?,?)";

                // Insert data into the choice tables
                if (type.equals("multiple")) {
                    // Create choice tables, each multiple choice table consist of question and choices
                    PreparedStatement ps_create = conn.prepareStatement(sql_create);
                    ps_create.executeUpdate();

                    qc = que_choice_pair.get(i);
                    String q = qc.get(0);
                    for (int a = 1; a < qc.size(); a++) {
                        String c = qc.get(i);
                        PreparedStatement ps_insert1 = conn.prepareStatement(sql1);
                        ps_insert1.setString(1,q);
                        ps_insert1.setString(2,c);
                        ps_insert1.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve the active survey from the server
     * Active survey means the due date is not expired
     * @return
     */
    public static ArrayList<String[]> getSurveyInfo (){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;

        ArrayList<String[]> basic_info = new ArrayList<>();
        try {
            conn = DBUtils.getConn();

            String sql = "select username, survey_title, due_date from survey";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String[] info = new String[3];
                info[0] = rs.getString(1);
                info[1] = rs.getString(2);
                info[2] = rs.getString(3);
              /*  info[3] = rs.getString(4);
                info[4] = rs.getString(5);
                info[5] = rs.getString(6);
                info[6] = rs.getString(7);*/
                if (DateValid(info[2])) {
                    basic_info.add(info); //if the due date is not expired, add it
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return basic_info;
    }

    /**
     * Get the corresponding questions information according to the title and the created user of a survey
     * @param username the username of the created person
     * @param title the title of a survey
     * @return an ArrayList<String[]> of all questions information
     */
    public static ArrayList<String[]> getQueryInfo(String username, String title){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;

        ArrayList<String[]> query = new ArrayList<>();

        try {
            conn = DBUtils.getConn();

            String sql = "select question,question_type, choice_num, limited_num from survey where survey_title = ? and username=?";
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setString(1,username);
            ps.setString(1,title);
            ps.setString(2,username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String[] info = new String[4];
                info[0] = rs.getString(1);
                info[1] = rs.getString(2);
                info[2] = rs.getString(3);
                info[3] = rs.getString(4);
                query.add(info);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return query;

    }


    /**
     * Get options based on the question
     * @param question the question
     * @return an ArrayList<String> contains all options
     */
    public static ArrayList<String> getChoicesInfo (String question){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;

        ArrayList<String> choices = new ArrayList<>();

        try {
            conn = DBUtils.getConn();

            String sql = "select choice from "+question;
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            String info = "";
            while(rs.next()){
                info = rs.getString(1);
                choices.add(info);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choices;
    }

    /**
     * Check whether the date is set after the current date
     * @param date
     * @return boolean
     */
    public static boolean DateValid (String date) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date current = new Date(System.currentTimeMillis());
        Date set = null;
        try {
            set = simpleFormat.parse(date);
            return set.after(current);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}

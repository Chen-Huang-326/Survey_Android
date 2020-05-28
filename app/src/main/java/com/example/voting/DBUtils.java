package com.example.voting;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DBUtils {

    private static final String TAG = "DBUtils";

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
}

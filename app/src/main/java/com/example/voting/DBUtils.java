package com.example.voting;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                    Log.d(TAG,"登录成功");
                    return true;
                }else{
                    Log.d(TAG,"登录失败");
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
                case 3:
                    sql = "insert into voting(title,c_1,c_2) values(?,?,?)";
                    break;
                case 4:
                    sql = "insert into voting(title,c_1,c_2,c_3) values(?,?,?,?)";
                    break;
                case 5:
                    sql = "insert into voting(title,c_1,c_2,c_3,c_4) values(?,?,?,?,?)";
                    break;
                case 6:
                    sql = "insert into voting(title,c_1,c_2,c_3,c_4,c_5) values(?,?,?,?,?,?)";
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
}

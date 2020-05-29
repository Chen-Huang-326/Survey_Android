package com.example.voting;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DBUtilsTest {

    private static boolean flag ;
    private static boolean submitFlag;
    private static ArrayList<String> information;
    @BeforeClass
    public static void arrange(){
        flag = false;
        submitFlag = true;
        information = new ArrayList<>();
        information.add("20");
        information.add("test@gmail.com");
        information.add("male");
    }

    @Test
    public void loginJudge() {
        assertEquals(flag,DBUtils.loginJudge("test","12345"));
    }

    @Test
    public void register(){
        boolean result = DBUtils.register("test","123456");
        assertEquals(flag,result);
    }


    @Test
    public void submitVote(){
        ArrayList<String> submit = new ArrayList<>();
        submit.add("What's your opinion?");
        submit.add("choice1");
        submit.add("choice2");
        submit.add("05/07/2020");
        boolean result = DBUtils.submitVote(submit);
        assertEquals(submitFlag,result);
    }

    @Test
    public void getInformation(){
        ArrayList getInfo = DBUtils.getInformation("test");
        boolean result;
        result = getInfo.equals(information);
        assertTrue("pass",result);
    }

    @Test
    public void DateValid(){
        boolean b = DBUtils.DateValid("07/09/2020");
        assertEquals(submitFlag,b);
    }
}
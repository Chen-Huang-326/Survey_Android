package com.example.voting;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SurveyTest {
    Survey survey = new Survey();

    /**
     * Check the date format
     */
    @Test
    public void checkDateFormat() {
        // Check leap year
        assertTrue("20/02/2020: expect true, but false", survey.checkDateFormat("29/02/2020"));
        assertFalse("29/02/2019: expect false, but true", survey.checkDateFormat("29/02/2019"));

        // Check the validation of day
        assertTrue("01/02/2020: expect true, but false", survey.checkDateFormat("01/02/2020"));
        assertFalse("35/03/2019: expect false, but true", survey.checkDateFormat("35/03/2019"));
        assertFalse("00/01/2020: expect false, but true", survey.checkDateFormat("00/01/2020"));

        // Check the validation of month
        assertTrue("20/01/2008: expect true, but false", survey.checkDateFormat("20/01/2008"));
        assertFalse("20/15/2009: expect false, but true", survey.checkDateFormat("20/15/2009"));
        assertFalse("20/00/2020: expect false, but true", survey.checkDateFormat("20/00/2020") );

        // Check the validation of year
        assertTrue("20/01/2017: expect true, but false", survey.checkDateFormat("20/01/2017"));
        assertTrue("20/05/0189: expect true, but false", survey.checkDateFormat("20/05/0189"));
        assertFalse("20/05/189: expect false, but true", survey.checkDateFormat("20/05/189"));

        // Check random illegal format
        assertFalse("expect 20/01/2017, but 20-01-2017", survey.checkDateFormat("20-01-2017"));
        assertFalse("expect 20/01/2017, but 20 01 2017", survey.checkDateFormat("20 01 2017"));
        assertFalse("illegal format, require dd/mm/yyy", survey.checkDateFormat("2020939409"));
        assertFalse("converted format, require dd/mm/yyyy", survey.checkDateFormat("2012/12/12"));
        assertFalse("length is over bounded", survey.checkDateFormat("2342535325235352353252"));

    }

    /**
     * Check the date validation
     */
    @Test
    public void DateValidationTest(){
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date current = new Date(System.currentTimeMillis());
        String currentDate = simpleFormat.format(current);
        int year = Integer.parseInt(currentDate.substring(6)) + 1;
        String futureDate = currentDate.substring(0,6) + year;

        // Check the due date equals established date = false;
        assertFalse("Date must be set after the date of establishing", survey.checkDateValid(currentDate));

        // Check the due date before established date = false;
        assertFalse("Due date cannot be set before established date", survey.checkDateValid("20/05/2020"));

        // Check the due date after established date is valid;
        assertTrue("The date is invalid", survey.checkDateValid(futureDate));
        
    }
}
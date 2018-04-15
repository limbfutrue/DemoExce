package com.libaoming.demoexce.demoexce.utils.formattime;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Libaoming on 2/1/2018.
 * 17 hour 09 minute
 * project_name : DemoExce
 */
public class DateUtilTest {
    @Test
    public void getCurrentYear() throws Exception {
        assertEquals(2018,DateUtil.getCurrentYear());
    }

    @Test
    public void getCurrentMonth() throws Exception {
        assertEquals(1,DateUtil.getCurrentMonth());
    }

    @Test
    public void getCurrentDay() throws Exception {
        assertEquals(2,DateUtil.getCurrentDay());
    }

    @Test
    public void getDayNum() throws Exception {
        assertEquals(31,DateUtil.getDayNum(2018,1));
    }

    @Test
    public void isLeapYear() throws Exception {
        assertEquals(false,DateUtil.isLeapYear(2018));
    }

    @Test
    public void getWeek() throws Exception {
        assertEquals(0,DateUtil.getWeek(2018,1,7));
    }

    @Test
    public void a() throws Exception {
        assertEquals(17,DateUtil.getCurrentHour());
        assertEquals(20,DateUtil.getCurrentMinite());
        assertEquals(17,DateUtil.getCurrentSecond());
    }

    @Test
    public void getOurSelData() throws Exception {
//        assertEquals("1",DateUtil.getOurSelData(20));
    }
}
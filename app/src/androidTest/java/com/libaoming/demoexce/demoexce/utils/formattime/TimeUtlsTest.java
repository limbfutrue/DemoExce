package com.libaoming.demoexce.demoexce.utils.formattime;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Libaoming on 28/12/2017.
 * 13 hour 28 minute
 * project_name : DemoExce
 */
public class TimeUtlsTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void formatTime() throws Exception {
    }

    @Test
    public void matcherStr() throws Exception {
        assertEquals("2321398243",TimeUtls.formatTime("4538"));
    }
}
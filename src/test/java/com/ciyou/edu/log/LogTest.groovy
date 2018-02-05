package com.ciyou.edu.log

import com.ciyou.edu.app.App
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @Author C.
 * @Date 2018-02-05 22:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
class LogTest {
    private static Logger logger = LoggerFactory.getLogger(LogTest.class)
    @Test
    void logTest(){
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
    }
}

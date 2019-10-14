package com.jason.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    private static final Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        new LogTest().test();
    }

    public void test() {
        int a = 0;
        try {
             a = 1 / 0;
        } catch (Exception e) {
            logger.info("异常日志测试-{}: ", a, e);
        }
    }
}

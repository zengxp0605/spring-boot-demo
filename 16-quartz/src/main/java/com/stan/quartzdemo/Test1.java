package com.stan.quartzdemo;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 */
public class Test1 {

    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;


    /**
     * Returns a random salt to be used to hash a password.
     *
     * @return a 16 bytes random salt
     */
    public static byte[] getNextSalt() {
        byte[] salt = new byte[8];
        RANDOM.nextBytes(salt);
        return salt;
    }


    public static void main(String[] args) throws UnsupportedEncodingException {


        try {

            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

//
//            // define the job and tie it to our HelloJob class
//            JobDetail job = newJob(HelloJob.class)
//                    .withIdentity("job1", "group1")
//                    .build();
//
//            // Trigger the job to run now, and then repeat every 40 seconds
//            Trigger trigger = new Trigger()
//                    .withIdentity("trigger1", "group1")
//                    .startNow()
//                    .withSchedule(simpleSchedule()
//                            .withIntervalInSeconds(40)
//                            .repeatForever())
//                    .build();
//
//            // Tell quartz to schedule the job using our trigger
//            scheduler.scheduleJob(job, trigger);
//

            // and start it off
            scheduler.start();

//            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }

        byte[] salt = getNextSalt();
        String str = new BASE64Encoder().encode(salt);
        str = str.replaceAll("/\\+\\=/", "");
        System.out.println(str);
    }
}

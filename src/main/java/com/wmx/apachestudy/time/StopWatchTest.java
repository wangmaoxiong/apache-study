package com.wmx.apachestudy.time;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.security.SecureRandom;

/**
 * Apache commons lang3 秒表 API StopWatch，用于计时，此类不是线程安全的
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2021/7/12 19:43
 */
public class StopWatchTest {

    /**
     * 秒表使用入门
     *
     * @throws InterruptedException
     */
    @Test
    public void testStopWatch1() throws InterruptedException {
        StopWatch stopwatch = StopWatch.createStarted();

        int nextInt = new SecureRandom().nextInt(4000);
        //2354
        System.out.println(nextInt);
        Thread.sleep(nextInt);

        stopwatch.stop();
        //方法执行结束，耗时：00:00:02.895
        System.out.println("方法执行结束，耗时：" + stopwatch.toString());
    }

    /**
     * 使用秒表统计大方法中各个子方法的执行时间
     *
     * @throws InterruptedException
     */
    @Test
    public void testStopWatch2() throws InterruptedException {
        StopWatch stopwatch_total = StopWatch.createStarted();
        StopWatch stopwatch = StopWatch.createStarted();

        SecureRandom secureRandom = new SecureRandom();

        int nextInt = secureRandom.nextInt(2000);
        System.out.println("任务1：" + nextInt);
        Thread.sleep(nextInt);
        System.out.println("任务1耗时" + stopwatch.toString());
        stopwatch.reset();
        stopwatch.start();

        nextInt = secureRandom.nextInt(2000);
        System.out.println("任务2：" + nextInt);
        Thread.sleep(nextInt);
        System.out.println("任务2耗时" + stopwatch.toString());
        stopwatch.reset();
        stopwatch.start();

        nextInt = secureRandom.nextInt(2000);
        System.out.println("任务3：" + nextInt);
        Thread.sleep(nextInt);
        System.out.println("任务3耗时" + stopwatch.toString());
        stopwatch.reset();
        stopwatch.start();

        nextInt = secureRandom.nextInt(2000);
        System.out.println("任务4：" + nextInt);
        Thread.sleep(nextInt);
        System.out.println("任务4耗时" + stopwatch.toString());
        stopwatch.reset();

        stopwatch_total.stop();
        System.out.println("方法总耗时：" + stopwatch_total.toString());
        /**
         * 任务1：1031
         * 任务1耗时00:00:01.744
         * 任务2：1104
         * 任务2耗时00:00:01.104
         * 任务3：1430
         * 任务3耗时00:00:01.430
         * 任务4：858
         * 任务4耗时00:00:00.858
         * 方法总耗时：00:00:05.144
         */
    }

}

package com.wmx.spring;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * spring framework 秒表 StopWatch API 使用
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2021/7/12 21:01
 */
@SuppressWarnings("all")
public class StopWatchTest {

    /**
     * StopWatch()：建造一个新的秒表，不启动任何任务。
     * void start()：启动未命名任务
     * start(String taskName)：启动命名任务，如果任务已经启动，则 IllegalStateException
     * * taskName要启动的任务的名称
     * stop()：停止当前任务，如果任务已经停止，则 IllegalStateException
     * long getTotalTimeMillis()：返回所有任务的总时间（毫秒）。
     * double getTotalTimeSeconds()：返回所有任务的总时间（以秒为单位）。
     * String toString()：返回描述所执行的所有任务的信息字符串
     *
     * @throws InterruptedException
     */
    @Test
    public void testStopWatch1() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();

        int nextInt = new SecureRandom().nextInt(4000);
        System.out.println(nextInt);//1236

        stopWatch.start("wmx");
        TimeUnit.MILLISECONDS.sleep(nextInt);

        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis() + "(毫秒)");//1237(毫秒)
        System.out.println(stopWatch.getTotalTimeSeconds() + "(秒)");//1.237(秒)
        System.out.println(stopWatch.toString());//StopWatch '': running time (millis) = 1237; [wmx] took 1237 = 100%
    }


}

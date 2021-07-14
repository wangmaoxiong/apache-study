package com.wmx.guava;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * google 的秒表 Stopwatch 相比 Spring framewrk core 包 和 apache commons lang3 包的秒表是最方便使用的
 * 此类不是线程安全的。
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2021/7/15 1:11
 */
@SuppressWarnings("all")
public class StopwatchTest {

    /**
     * Stopwatch createStarted()：创建（并启动）一个新的秒表，使用 System#nanoTime 来作为其时间源。
     * Stopwatch createUnstarted()：创建（但不启动）一个新的秒表，使用 System#nanoTime 来作为其时间源。
     * long elapsed(TimeUnit desiredUnit)：返回此秒表上显示的当前已用时间，以所需的时间单位表示，任何分数向下舍入
     * boolean isRunning()：如果已在此秒表上调用start（）}，并且自上次调用start（）以来未调用stop（），则返回true
     * Stopwatch reset()：将此秒表的运行时间设置为零，并将其置于停止状态。
     * Stopwatch start()：启动秒表,如果秒表已经在运行，则 IllegalStateException
     * Stopwatch stop()：停止秒表，将来的读取将返回到目前为止经过的固定持续时间。
     * tring toString()：返回当前运行时间的字符串表示形式，比如 2.588 s，106.8 ms
     */
    @Test
    public void testStopwatch() throws InterruptedException {
        SecureRandom secureRandom = new SecureRandom();
        Stopwatch stopwatch = Stopwatch.createStarted();

        int nextInt = secureRandom.nextInt(2000);
        System.out.println("任务1预算耗时：" + nextInt);//任务1预算耗时：81
        TimeUnit.MILLISECONDS.sleep(nextInt);
        System.out.println("\t任务1实际耗时：" + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "(毫秒)");//	任务1实际耗时：563(毫秒)

        stopwatch.reset().start();
        nextInt = secureRandom.nextInt(4000);
        System.out.println("任务2预算耗时：" + nextInt);//任务2预算耗时：1591
        TimeUnit.MILLISECONDS.sleep(nextInt);
        System.out.println("\t任务2实际耗时：" + stopwatch.toString());//	任务2实际耗时：1.592 s

        stopwatch.reset().start();
        nextInt = secureRandom.nextInt(3000);
        System.out.println("任务3预计耗时：" + nextInt);//任务3预计耗时：1964
        TimeUnit.MILLISECONDS.sleep(nextInt);
        System.out.println("\t任务3实际耗时：" + stopwatch.stop().toString());//	任务3实际耗时：1.965 s
    }

}

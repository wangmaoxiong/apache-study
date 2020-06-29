package com.wmx.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Jupiter 断言
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/29 9:00
 */
public class AssertionsDemo {
    private String firstName;
    private String lastName;

    @BeforeEach
    public void init() {
        firstName = "John";
        lastName = "Doe";
    }

    @Test
    void standardAssertions() {
        assertEquals(2, 2);
        assertEquals(4, 4, "两个参数不相等");
        assertTrue(2 == 3, () -> "表达式返回 false");
    }

    @Test
    void groupedAssertions() {
        //在一个分组断言中，所有断言都将被执行，任何失败都将一起报告。
        assertAll("person",
                () -> assertEquals("John", firstName),
                () -> assertEquals("Doe", lastName)
        );
    }

    @Test
    void dependentAssertions() {
        //在代码块中，如果断言失败，则将跳过同一块中的后续代码。
        assertAll("properties",
                () -> {
                    assertNotNull(firstName);
                    // 仅在前面的断言有效时执行
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("J")),
                            () -> assertTrue(firstName.endsWith("n"))
                    );
                },
                () -> {
                    //分组断言
                    assertNotNull(lastName);
                    //仅在前面的断言有效时执行
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("D")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }

    @Test
    void exceptionTesting() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // 下面的方法断言成功
        assertTimeout(ofMinutes(2), () -> {
            System.out.println(".....");
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // 以下断言成功，并返回提供的对象。
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        //下面的断言调用方法引用并返回一个对象。
        String actualGreeting = assertTimeout(ofMinutes(2), AssertionsDemo::greeting);
        assertEquals("hello world!", actualGreeting);
    }

    @Test
    void timeoutExceeded() {
        //以下断言失败，错误消息类似于：执行超过超时10毫秒91毫秒
        assertTimeout(ofMillis(10), () -> {
            //模拟超过10毫秒的任务。
            Thread.sleep(100);
        });
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        //以下断言失败，错误消息类似于：执行在10毫秒后超时
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // 模拟超过10毫秒的任务。
            Thread.sleep(100);
        });
    }

    private static String greeting() {
        return "hello world!";
    }
}
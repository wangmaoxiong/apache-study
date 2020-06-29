package com.wmx.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/6/29 8:49
 */

public class StandardTests {
    /**
     * @BeforeAll 最先运行，方法必须是 static 修饰
     * @BeforeEach 第二个运行
     * @Test 测试方法运行
     * @AfterEach 倒数第二个运行
     * @AfterAll 最后运行，方法必须是 static 修饰
     */

    @BeforeAll
    static void initAll() {
        System.out.println("BeforeAll");
    }

    @BeforeEach
    void init() {
        System.out.println("BeforeEach");
    }

    @Test
    void succeedingTest() {
        assertNotNull(100);
    }

    @Test
    void failingTest() {
        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("AfterAll");
    }
}
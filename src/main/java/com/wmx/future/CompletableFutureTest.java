package com.wmx.future;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * CompletableFuture 并发工具类演示
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/8/12 20:12
 */
public class CompletableFutureTest {
    private static Logger logger = LoggerFactory.getLogger(CompletableFutureTest.class);


    /**
     * 通过构造函数构建 CompletableFuture 对象
     * T get()：在子线程任务执行结束之前主线程将一直处在阻塞状态
     * T get(long timeout, TimeUnit unit)：最长等待任务执行时间
     */
    @Test
    public void testGet1() {
        try {
            int waitTime = 10;
            CompletableFuture<String> completableFuture = new CompletableFuture<String>();
            logger.info("我开始等待子线程执行，最长等待 {} 秒", waitTime);
            String result = completableFuture.get(waitTime, TimeUnit.SECONDS);
            logger.info("子线程执行结果：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建 CompletableFuture 对象：通过构造函数构建
     * boolean complete(T value)：如果任务尚未完成，则设置 get 方法返回的值，这样 get 方法就会立刻返回，不会继续阻塞。
     */
    @Test
    public void testGet2() {
        try {
            int waitTime = 10;
            CompletableFuture<String> completableFuture = new CompletableFuture<String>();
            completableFuture.complete(" Hello CompletableFuture ！");
            logger.info("我开始等待子线程执行，最长等待 {} 秒", waitTime);
            String result = completableFuture.get(10, TimeUnit.SECONDS);
            logger.info("子线程执行结果：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * CompletableFuture<Void> runAsync(Runnable runnable)：异步执行任务，无返回值
     * 1、函数式接口 Runnable 的 run 方法是无参数、无返回值的，所以 get 方法也没有返回值
     * 2、子线程是守护线程，所以这个测试方法如果没有 get 阻塞主线程，它会立刻结束。
     * 3、默认使用 {@link ForkJoinPool} 线程吃来执行任务，其中的线程默认是守护线程
     */
    @Test
    public void runAsyncTest1() {
        try {
            int nextInt = new Random().nextInt(3) + 1;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(nextInt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("我是子线程，数据库操作完成！我是守护线程吗？" + Thread.currentThread().isDaemon());
            });
            logger.info("测试方法主线程主动阻塞，等待子线程执行任务，预计耗时 {} 秒！", nextInt);
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * CompletableFuture<Void> runAsync(Runnable runnable,Executor executor)，异步执行任务，无返回值
     * 1、返回一个新的 CompletableFuture，由给定线程池（executor）中的线程异步运行任务（runnable）
     * 2、自定义线程池中的线程默认是非守护线程。
     */
    @Test
    public void runAsyncTest2() {
        try {
            //自定义线程池
            ExecutorService executorService = Executors.newFixedThreadPool(5);

            int nextInt = new Random().nextInt(3) + 1;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(nextInt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("我是自定义线程池，数据库操作完成！我是守护线程吗？" + Thread.currentThread().isDaemon());
            }, executorService);
            logger.info("测试方法主线程主动阻塞，等待子线程执行任务，预计耗时 {} 秒！", nextInt);
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * CompletableFuture<U> supplyAsync(Supplier<U> supplier)，异步执行任务，有返回值
     * 1、runAsync 是没有返回结果的，想获取异步计算的返回结果可以使用 supplyAsync() 方法
     */
    @Test
    public void supplyAsyncTest1() {
        try {
            int sleepTime = new Random().nextInt(3) + 1;
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("数据库操作完毕.");
                return UUID.randomUUID().toString();
            });

            logger.info("开始等待子线程异步执行的结果，预计时间 {} 秒", sleepTime);
            String result = future.get();
            logger.info("子线程返回结果：{}", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * CompletableFuture<U> supplyAsync(Supplier<U> supplier)：异步执行任务，有返回值
     * CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)：串行执行任务。上一个任务执行的结果将作为下一个任务的参数。
     * 1、当此阶段正常完成时，将此阶段的结果作为所提供函数的参数来执行，串行的最终任务会有返回值。
     * 2、串行的后续操作并不一定会和前序操作使用同一个线程，但通常是同一个线程串行执行任务
     */
    @Test
    public void thenApplyTest1() {
        try {
            int sleepTime = new Random().nextInt(3) + 1;
            CompletableFuture<String> comboText = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("远程调用用户服务完毕，返回结果。");
                return "userId:20002";
            }).thenApply(userId -> {
                try {
                    TimeUnit.SECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("远程调用排行榜服务完毕,返回结果。");
                return userId + "_" + "rankingList:1,2,3,4,55";
            }).thenApply(finalData -> finalData);

            logger.info("开始等待子线程异步执行的结果，预计时间 {} 秒", sleepTime * 2);
            String resultData = comboText.get();
            logger.info("子线程全部执行完毕，返回结果：{}", resultData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * CompletableFuture<Void> thenAccept(Consumer<? super T> action)：串行执行任务
     * 1、当上一阶段正常完成时，将其返回结果作为自己的参数来执行新任务
     * 2、而 thenAccept 方法的Lambda 函数自己是没有返回值，当继续串行 thenAccept 时，后续的参数就会为 null。
     */
    @Test
    public void thenAcceptTest1() {
        try {
            int sleepTime = new Random().nextInt(3) + 1;
            CompletableFuture<Void> completableFuture =
                    CompletableFuture.supplyAsync(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(sleepTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        logger.info("数据库查询完成。");
                        return new Random().nextInt(Integer.MAX_VALUE);
                    }).thenAccept(product1 -> {
                        //上一个任务的 supplyAsync 方法的Lambda 函数的返回值作为这里的参数（product1）
                        try {
                            TimeUnit.SECONDS.sleep(sleepTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        logger.info("向用户微服务推送数据完成，参数:{}", product1);
                    }).thenAccept(product2 -> {
                        try {
                            //上一个任务（thenAccept）的Lambda 函数无返回值，所以这里的参数（product2）会为 null.
                            TimeUnit.SECONDS.sleep(sleepTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        logger.info("向订单微服务推送数据完成，参数：{}", product2);
                    });
            logger.info("测试方法主线程主动阻塞，等待子线程执行任务，预计等待时间为 {} 秒", sleepTime * 3);
            // 因为串行最后的 thenAccept 方法是无返回值的，所以这里 get 也就无返回值.
            completableFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * CompletableFuture<Void> thenRun(Runnable action)：串行执行任务，函数式接口 Runnable 的 Lambda 函数无参数无返回值.
     */
    @Test
    public void thenRunTest1() {
        int sleepTime = new Random().nextInt(3) + 1;
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("用户微服务调用结束！");
        }).thenRun(() -> {
            try {
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("订单微服务调用结束！");
        });
        try {
            logger.info("测试方法主线程主动阻塞，等待子线程执行任务，预计时间 {} 秒。", sleepTime * 2);
            completableFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
     * 1、当上一阶段正常完成时，将使用提供的 Executor 线程池执行当前任务
     * 2、与 thenApply(Function<? super T,? extends U> fn) 的区别就是是否使用自定义线程池执行新任务
     * 3、其它的 thenXxx 方法都有相同的方法
     * 4、可以发现下面的 thenApplyAsync 方法执行任务的线程不再是同一个线程
     */
    @Test
    public void thenApplyAsyncTest1() {
        try {
            int sleepTime = new Random().nextInt(3) + 1;
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("调用用户微服务结束");
                Map<String, Object> dataMap = new HashMap<>(2);
                dataMap.put("userName", "张三");
                return dataMap;
            }).thenApplyAsync((Function) object -> {
                try {
                    TimeUnit.SECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("调用电影微服务结束");
                Map<String, Object> dataMap = (Map<String, Object>) object;
                dataMap.put("movie", "智取威虎山");
                return dataMap;
            }, executorService).thenApplyAsync((Function) obj -> {
                logger.info("数据处理返回。");
                return obj;
            }, executorService);

            logger.info("测试方法主线程主动阻塞等待子线程执行任务，预计时间为 {} 秒", sleepTime * 2);
            Map<String, Object> dataMap = (Map<String, Object>) completableFuture.get();
            logger.info("Over：" + dataMap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * CompletableFuture<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn)
     * 1、聚合两个独立 Future 的结果
     * 2、当任务1（调用此方法的任务）和任务2（other）都正常完成时，将它们的两个返回结果作为 fn 的参数执行
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void thenCombineTest1() throws InterruptedException, ExecutionException {
        //异步执行计算体重
        CompletableFuture<Double> weightFuture = CompletableFuture.supplyAsync(() -> {
            logger.info("开始用户体重测量.");
            try {
                int sleepTimeMs = new Random().nextInt(4) + 1;
                TimeUnit.SECONDS.sleep(sleepTimeMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("用户体重测量完毕.");
            return 65.60;
        });
        //异步执行计算身高
        CompletableFuture<Double> heightFuture = CompletableFuture.supplyAsync(() -> {
            logger.info("开始用户身高测量.");
            try {
                int sleepTimeMs = new Random().nextInt(4) + 1;
                TimeUnit.SECONDS.sleep(sleepTimeMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("用户身高测量完毕.");
            return 174.45;
        });

        logger.info("测试主线程主动阻塞，等待子线程计算用户数据，然后合并结果再新开子线程进行处理.");
        CompletableFuture<Double> combinedFuture = weightFuture
                .thenCombine(heightFuture, (weight, height) -> {
                    // 参数weight的值是weightFuture任务的返回结果；参数height的值是heightFuture任务返回的结果
                    logger.info("开始计算用户 BMI 数据，weight={}，height={}", weight, height);
                    Double heightInMeter = height / 100;
                    return weight / (heightInMeter * heightInMeter);
                });
        Double bmi = combinedFuture.get();
        logger.info("用户身体 BMI 指标为：{} ", bmi);
    }

    /**
     * CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)：返回 CompletableFuture 的 get 方法会等待所有 cfs 执行完成
     * CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)：返回 CompletableFuture 的 get 方法会等待 cfs 中任意一个返回
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void allOfTest1() throws ExecutionException, InterruptedException {
        //异步执行任务 1
        CompletableFuture userFuture = CompletableFuture.runAsync(() -> {
            int sleepTimeMs = new Random().nextInt(4) + 1;
            logger.info("向用户微服务推送完毕，预计 {} 秒.", sleepTimeMs);
            try {
                TimeUnit.SECONDS.sleep(sleepTimeMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("向用户微服务推送完毕.");
        });

        //异步执行任务 2
        CompletableFuture movieFuture = CompletableFuture.runAsync(() -> {
            int sleepTimeMs = new Random().nextInt(8) + 3;
            logger.info("向电影微服务推送数据开始，预计 {} 秒.", sleepTimeMs);
            try {
                TimeUnit.SECONDS.sleep(sleepTimeMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("向电影微服务推送数据完毕.");
        });

        // allOf：所有任务全部执行完成时，返回 CompletableFuture 的 get 方法才会返回，否则一直等待所有任务执行完成
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(userFuture, movieFuture);
        Object result = completableFuture.get();
//
        // anyOf：其中任意一个任务执行完成时，返回 CompletableFuture 的 get 方法会立刻返回，否则一直等待第一个任务执行完成，其余任务仍然会继续执行.
//        CompletableFuture<Object> completableFuture = CompletableFuture.anyOf(userFuture, movieFuture);
//        Object result = completableFuture.get();

        System.out.println(result);
        Thread.sleep(10 * 1000);
    }

    /**
     * CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn)
     * 1、exceptionally 相当于 try-catch-finally 中的 catch，只要出现异常，将会跳过 thenXxx 串行的后续操作，直接捕获异常，进行处理
     * 2、既如果 CompletableFuture< 任务执行失败，抛出异常，则会进入 exceptionally，lambda 函数参数就是异常对象
     * 3、如果任务执行没有发生异常，则不会进入 exceptionally。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void exceptionallyTest1() throws ExecutionException, InterruptedException {
        boolean isException = true;
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            int sleepTime = new Random().nextInt(5);
            logger.info("开始查询数据库，预计时间 {} 秒", sleepTime);
            try {
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isException) {
                throw new IllegalArgumentException("参数不能为空！");
            }
            return "200";
        }).thenApply((item) -> {
            int sleepTime = new Random().nextInt(3);
            logger.info("开始调用用户微服务，预计时间 {} 秒", sleepTime);
            try {
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "200";
        }).exceptionally((ex) -> {
            logger.error("任务执行失败,cause by :{}", ex.getMessage());
            return "500";
        });
        String msgCode = completableFuture.get();
        System.out.println(msgCode);
    }

    /**
     * CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn)
     * 1、handle 方法相当于 try-catch-finally 中的 finally，无论任务执行是否成功都会进入此方法
     * 2、lambda 函数接收两个参数，第一个参数是前一个回调函数的返回值，后一个参数是异常对象，为 null 表示无异常
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void handleTest1() throws ExecutionException, InterruptedException {
        boolean isException = true;
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            int sleepTime = new Random().nextInt(5);
            logger.info("开始操作数据库，预计时间 {} 秒", sleepTime);
            try {
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isException) {
                ServerSocket serverSocket = null;
                try {
                    logger.info("TCP 开始监听本地 8080 端口.");
                    serverSocket = new ServerSocket(8080);
                    serverSocket.setSoTimeout(3000);
                    serverSocket.accept();
                } catch (IOException e) {
                    throw new RuntimeException("等待连接超时", e);
                }
            }
            return "200";
        }).thenApply((item) -> {
            int sleepTime = new Random().nextInt(3);
            logger.info("开始向 kafka 推送消息，预计时间 {} 秒", sleepTime);
            try {
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "200";
        }).handle((res, ex) -> {
            logger.info("所有任务正常执行完毕或者出现异常，都会执行我.");
            if (ex != null) {
                logger.error("任务执行失败,cause by :{}", ex.getMessage());
                return "500";
            }
            return res;
        });
        logger.info("测试方法主线程主动阻塞，等待异步任务执行完毕！");
        String msgCode = completableFuture.get();
        logger.info("任务执行完毕，返回状态码：{} (200 表示成功，500 表示错误)", msgCode);
    }

    /**
     * boolean cancel(boolean mayInterruptIfRunning)
     * 1、如果 CompletableFuture 尚未执行完成，则可以调用此方法取消任务
     * 2、mayInterruptIfRunning：此实现中没有任何影响，因为中断不用于控制处理。
     * 3、如果任务取消成功，则返回 true
     * boolean isCancelled()：如果此CompletableFuture在正常完成之前被取消，则返回 true
     * boolean isDone()：如果以任何方式完成任务，则返回 true：比如异常情况，或通过取消、或正常完成等。
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void cancelTest1() throws InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            ServerSocket serverSocket = null;
            try {
                logger.info("TCP 开始监听本地 8080 端口.");
                serverSocket = new ServerSocket(8080);
                serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logger.info("测试方法主线程主动阻塞 3 秒");
        TimeUnit.SECONDS.sleep(3);
        logger.info("测试方法主线程等待时间超过 3 秒，强制结束子线程.");
        boolean cancel = completableFuture.cancel(false);
        System.out.println("是否取消成功？" + cancel);


    }

}

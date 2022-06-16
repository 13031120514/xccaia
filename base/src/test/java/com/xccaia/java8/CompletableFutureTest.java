package com.xccaia.java8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CompletableFutureTest {

    // 加法 add
// 减法 subtract
// 乘法 multiply
// 除法 divide
    public static Integer multiply(Integer para) {
        try {
            // 模拟长时间计算
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
    }

    public static Integer divide(Integer para) {
        try {
            // 模拟长时间计算
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para / 2;
    }


    /**
     * 方法不能是静态的,不能执行junitTest
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
//    public  static void test1() throws ExecutionException, InterruptedException {
    public void test1() throws ExecutionException, InterruptedException {


        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> multiply(50))
                .thenApply(i -> Integer.toString(i))
//                .thenApply(str -> str+"--"+1/0) // 模拟报错捕获异常
                .exceptionally(ex -> {
                    //ex.printStackTrace();
                    System.out.println(ex.toString());
                    return "8888";
                })
                .thenApply(str -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        System.out.println(future);
        future.get();
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {


        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> divide(50))
                .thenCompose((i) -> CompletableFuture.supplyAsync(() -> divide(i)))
                .thenApply(j -> Integer.toString(j))
//                .thenApply(str -> str+"--"+1/0) // 模拟报错捕获异常
                .exceptionally(ex -> {
                    //ex.printStackTrace();
                    System.out.println(ex.toString());
                    return "8888";
                })
                .thenApply(str -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        System.out.println(future);
        future.get();
    }


}

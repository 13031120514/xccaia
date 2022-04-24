package com.xc.concurrent;

import com.sgcc.eip.cmc.cable.biz.acquisition.dto.msg.SystemCopy;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> future = null;
        System.out.println("------multiply-------");
        System.out.println("------multiply-------");
      /*  future = CompletableFuture.supplyAsync(() -> multiply(50))
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

        System.out.println("------divide-------");
        System.out.println("------divide-------");


        future = CompletableFuture.supplyAsync(() -> divide(50))
                .thenCompose((i) -> CompletableFuture.supplyAsync(() -> divide(i)))
                .thenApply(j -> Integer.toString(j))
//                .thenApply(str -> str+"--"+1/0) // 模拟报错捕获异常
                .exceptionally(ex -> {
                    //ex.printStackTrace();
                    System.out.println(ex.toString());
                    return "8888";
                })
                .thenApply(str -> "\"" + str + "\"")
                .thenAccept(System.out::println)
        ;
        System.out.println(future);
        future.get();

        System.out.println("-------------");
        System.out.println("-------------");*/


        CompletableFuture<String> stringFuture = CompletableFuture.supplyAsync(() -> divide(50))
                .thenCompose((i) -> CompletableFuture.supplyAsync(() -> divide(i)))//可以进行组合调用
                .thenCompose((i) -> CompletableFuture.supplyAsync(() -> multiply(i)))//可以进行组合调用
                .thenApply(j -> Integer.toString(j))
//                .thenApply(str -> str+"--"+1/0) // 模拟报错捕获异常
//                .exceptionally(ex -> {
//                    //ex.printStackTrace();
//                    System.out.println(ex.toString());
//                    return "8888";
//                })
                .thenApply(str -> "\"" + str + "\"")
//                .thenAcceptAsync()
                ;
        System.out.println(stringFuture);
        stringFuture.get();
        System.out.println("AAAAAAAAAAAAA");
        System.out.println(stringFuture.get());


        CompletableFuture<Integer> intCompletableFuture = CompletableFuture.supplyAsync(() -> divide(50));
        CompletableFuture<Integer> intCompletableFuture2 = CompletableFuture.supplyAsync(() -> divide(25));
        intCompletableFuture.thenCombine(intCompletableFuture2, (i, j) -> i + j)
                .thenApply(value -> "\"" + value + "\"")// public <U> CompletableFuture<U> thenApply 有返回值
                .thenAccept(System.out::println);// public CompletableFuture<Void> thenAccept 无返回值

    }


}

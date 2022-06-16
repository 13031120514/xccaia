package com.sgcc.eip.cmc.cable.biz.acquisition.dto.msg;


import java.util.concurrent.atomic.AtomicInteger;

class MyData
{
    // volatile  volatile
     int number = 0;

    public void add10() {
        this.number += 10;
    }



}

class MyData2 {
    volatile int number = 0;

    AtomicInteger atomicInteger = new AtomicInteger();
    public void add() {
        number++;
        atomicInteger.getAndIncrement();
    }
}


public class VolatileTest {

    public static void main(String[] args) {
//        MyData myData = new MyData();
//        // 启动一个线程修改myData的number，将number的值加10
//        new Thread(
//                () -> {
//                    System.out.println("线程" + Thread.currentThread().getName()+"\t 正在执行");
//                    try{
//                        TimeUnit.SECONDS.sleep(3);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    myData.add10();
//                    System.out.println("线程" + Thread.currentThread().getName()+"\t 更新后，number的值为" + myData.number);
//                }
//        ).start();
//
//        // 看一下主线程能否保持可见性
//        int i=0;
//        while (myData.number == 0) {
////            System.out.println("11");
//            i++;// 1636068320
//            // 当上面的线程将number加10后，如果有可见性的话，那么就会跳出循环；
//            // 如果没有可见性的话，就会一直在循环里执行
//        }
//        System.out.println(i);
//        System.out.println("具有可见性！");



        MyData2 myData = new MyData2();

        // 启动20个线程，每个线程将myData的number值加1000次，那么理论上number值最终是20000
        for (int i=0; i<20; i++) {
            new Thread(() -> {
                for (int j=0; j<1000; j++) {
                    myData.add();
                }
            }).start();
        }

        // 程序运行时，模型会有主线程和守护线程。如果超过２个，那就说明上面的２０个线程还有没执行完的，就需要等待
        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println("number值加了20000次，此时number的实际值是：" + myData.number);
        System.out.println("num值加了20000次，此时 atomicInteger 的实际值是：" + myData.atomicInteger);

    }
}

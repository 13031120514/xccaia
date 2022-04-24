package com.xc;

import org.junit.Test;

public class StringNewTest {


    @Test
    public void string1() {

        /**
         * 字符串常量池  String pool  是一个固定的大小的HashTable数据默认值是1009
         *
         * 使用-XX:StirngTableSize
         */


        /**
         *
         * String a = new String("ab"); 创建几个对象
         * 一个对象 new关键字在堆空间创建的 String对象
         * 另一个对象是  字符串常量池中的对象 ab ldc 字节码指令 ldc
         *
         */

//        String str1 = new String("ab");

        /**
         *
         * 1. new StringBuilder
         * 2. new String("a")
         * 3.字符串常量池中的对象 a
         * 4. new String("b")
         * 5.字符串常量池中的对象 b
         *
         * 深入 StirngBuilder.toString方法  会调用new Stirng
         * 6.还会创建一个 new String("ab")
         * 下面是没有  ab的字符串常量池
         */
        String str2 = new String("a")+new String("b");


    }


    @Test
    public void test21(){


        System.out.println();
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");

        System.out.println();
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");

    }


}

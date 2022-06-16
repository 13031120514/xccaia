package com.xccaia.spring.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringApplication {


    @Bean
    public MyBeanPostProcessor getMyBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }


    public static void main(String[] args) {
        SpringApplication.run(MySpringApplication.class, args);
      System.out.println(111);
    }
}

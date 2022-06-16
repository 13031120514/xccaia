package com.xccaia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ Author     ：xccaia
 * @ Date       ：2019/11/19 21:16
 * @ Description：
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class BaseApplication {

  public static void main(String[] args) {
    SpringApplication.run(BaseApplication.class, args);
  }
}

package com.xccaia.user;

import com.github.pagehelper.PageHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * @ Author     ：xccaia
 * @ Date       ：2021-03-24
 * @ Description： swagger 用户信息入口启动类
 */
@SpringBootApplication
public class UserSpringApplication  {

  @Bean
  public PageHelper pageHelper() {
    PageHelper pageHelper = new PageHelper();
    Properties properties = new Properties();
    properties.setProperty("offsetAsPageNum", "true");
    properties.setProperty("rowBoundsWithCount", "true");
    properties.setProperty("reasonable", "true");
    properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言111
    pageHelper.setProperties(properties);
    return pageHelper;
  }

  public static void main(String[] args) {


    String str=new String("11");
    char[] chars = str.toCharArray();
    char char0 = str.charAt(0);

    //TODO  Cglib 类可以直接全部打印出到指定的路径 ,是不是debug都能打印 打印动态代理的class文件
    System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\class");
//    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");
    SpringApplication.run(UserSpringApplication.class, args);
    System.out.println("swagger-ui 的地址是");
    System.out.println("http://localhost:8801/swagger-ui.html");
  }

}

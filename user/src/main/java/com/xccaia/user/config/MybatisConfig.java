//package com.xccaia.user.config;
//
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.context.annotation.Bean;
//
//import com.github.pagehelper.PageHelper;
//
//import java.util.Properties;
//
//@SpringBootConfiguration
//public class MybatisConfig {
//
//    @Bean
//    public PageHelper pageHelper() {
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("offsetAsPageNum", "true");
//        properties.setProperty("rowBoundsWithCount", "true");
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
//        pageHelper.setProperties(properties);
//        return pageHelper;
//    }
//}
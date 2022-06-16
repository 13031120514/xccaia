package com.xccaia.user.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xccaia.user.mapper.UserMapper;
import com.xccaia.user.mapper.UserSampleMapper;
import com.xccaia.user.model.PageableQueryDTO;
import com.xccaia.user.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @ Author     ：xccaia
 * @ Date       ：2021-03-24
 * @ Description： swagger 用户信息处理
 */
@RestController
@Api(value = "UserController2 : 用户信息处理")
public class UserController2 implements BeanFactoryAware {

    final String BASE_URL = "/user2";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSampleMapper userSampleMapper;

    @PostMapping(value = BASE_URL + "/selectAll")
    @ResponseBody
    @ApiOperation("通用mapper生成sql")
    public List<User> selectAll() {
        return userMapper.selectAll();
    }


    @PostMapping(value = BASE_URL + "/selectAll2222")
    @ResponseBody
    @ApiOperation("通用mapper生成sql")
    public PageInfo<User> selectAll2(@RequestBody PageableQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO);

        List<User> list = userMapper.selectAll();
        return new PageInfo<>(list);
    }

    @PostMapping(value = BASE_URL + "/selectAll3")
    @ResponseBody
    @ApiOperation("selectAll3")
    public PageInfo<User> selectAll3(@RequestBody PageableQueryDTO queryDTO) {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        PageHelper.startPage(1, 2);
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        List<User> list = userMapper.selectAll();
//        System.out.println(beanFactory);
        return new PageInfo<>(list);
    }


    @PostMapping(value = BASE_URL + "/selectAll4")
    @ResponseBody
    @ApiOperation("selectAll4")
    public PageInfo<User> selectAll4(@RequestBody PageableQueryDTO queryDTO) {
        saveProxyFile(userMapper.getClass().getSimpleName(),"1",UserMapper.class);
        saveProxyFile(userSampleMapper.getClass().getSimpleName(),"1", UserSampleMapper.class);
        PageHelper.startPage(1, 2);
        saveProxyFile(userSampleMapper.getClass().getSimpleName(),"2", UserSampleMapper.class);
        List<User> list = userSampleMapper.findSelectAllXccaia();// TODO  返回的list已经是被d代理的Page对象了,Page继承了ArrayList
        saveProxyFile(userSampleMapper.getClass().getSimpleName(),"3", UserSampleMapper.class);
//        System.out.println(beanFactory);
        return new PageInfo<>(list);
    }
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    // 下载名字   代理的class文件
    private static void saveProxyFile(String className,String index,Class clazz) {
        FileOutputStream out = null;
        try {// ProxyGenerator.generateProxyClass("$Proxy85@8131", MapperProxy.class.getInterfaces());  ProxyGenerator.generateProxyClass("$Proxy0", MapperProxy.class.getInterfaces());
//            byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy85@8131", MapperProxy.class.getInterfaces());
            byte[] classFile = ProxyGenerator.generateProxyClass(className, new Class[]{clazz});
            out = new FileOutputStream("F:\\Desktop\\dailyTest\\delete\\MapperProxy" +className+"-"+ index+".class");
            out.write(classFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void saveProxyFile(String className) {
        FileOutputStream out = null;
        try {// ProxyGenerator.generateProxyClass("$Proxy85@8131", MapperProxy.class.getInterfaces());  ProxyGenerator.generateProxyClass("$Proxy0", MapperProxy.class.getInterfaces());
//            byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy85@8131", MapperProxy.class.getInterfaces());
            byte[] classFile = ProxyGenerator.generateProxyClass(className, new Class[]{UserMapper.class});
            out = new FileOutputStream("F:\\Desktop\\dailyTest\\delete\\MapperProxy" +className+ ".class");
            out.write(classFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void saveProxyFile() {
        FileOutputStream out = null;
        try {// ProxyGenerator.generateProxyClass("$Proxy85@8131", MapperProxy.class.getInterfaces());  ProxyGenerator.generateProxyClass("$Proxy0", MapperProxy.class.getInterfaces());
//            byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy85@8131", MapperProxy.class.getInterfaces());
            byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy85@8131", new Class[]{UserMapper.class});
            out = new FileOutputStream("F:\\Desktop\\dailyTest\\delete\\MapperProxy" + "$Proxy85@8131222.class");
            out.write(classFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        HashMap map = new HashMap<>();

        System.out.println(map.put(null,null));
        System.out.println(map.put(1,null));
        System.out.println(map.put(1,null));
        System.out.println(map.put(1,"5gyhg"));

    }


}

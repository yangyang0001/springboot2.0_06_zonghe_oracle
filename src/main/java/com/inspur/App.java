package com.inspur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 外置tomcat 部署war包的时候必须要 修改以下2处的东西
 * User: YANG
 * Date: 2019/6/13-0:29
 * Description: No Description
 */
@SpringBootApplication
@EnableAsync    //开启异步调用的方式
//@MapperScan(basePackages = {"com.inspur.user.mapper","com.inspur.order.mapper"})
//public class App extends SpringBootServletInitializer { //外置tomcat 部署的时候 修改1
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
    /**
     * 外置tomcat 部署的时候 修改2
     * @param builder
     * @return
     */
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(App.class);
//    }
}

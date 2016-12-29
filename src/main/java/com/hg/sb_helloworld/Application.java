
package com.hg.sb_helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Copyright © 2016 Ubtech. All rights reserved.
 * 
 * @Title: Application.java
 * @Prject: sb-helloworld
 * @Package: com.ubt.hg.sb_helloworld.application
 * @Description: spring boot hello world demo project
 * @author: HuGui
 * @date: 2016年10月28日 上午9:55:05
 * @version: V1.0
 */

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication.run( Application.class, args );
    }
}

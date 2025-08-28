
package com.gm.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: xexgm
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class App {

    public static void main(String[] args) throws IllegalAccessException {
        SpringApplication.run(App.class, args);
    }
}

package com.demo.spring5;

import com.demo.spring5.repository.MySQLRepository;
import com.demo.spring5.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Spring5Application {

    @Autowired
    public MySQLRepository repository;

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(Spring5Application.class)
                .initializers((ApplicationContextInitializer<GenericApplicationContext>) ctx -> {
                    System.out.println("在程序运行前向上下文注入SampleService");
                    ctx.registerBean("sampleService", SampleService.class, SampleService::new);
                })
                .run(args);
    }

    @PostConstruct
    public void begin() {
        System.out.println(repository.findNames());
    }

}

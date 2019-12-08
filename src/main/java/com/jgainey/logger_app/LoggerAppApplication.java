package com.jgainey.logger_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggerAppApplication.class, args);
        Utils.initLogger();
    }

}

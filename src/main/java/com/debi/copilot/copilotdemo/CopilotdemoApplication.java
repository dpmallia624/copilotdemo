package com.debi.copilot.copilotdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CopilotdemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(CopilotdemoApplication.class);

    public static void main(String[] args) {
        logger.info("Starting CopilotdemoApplication...");
        SpringApplication.run(CopilotdemoApplication.class, args);
        logger.info("CopilotdemoApplication started.");
    }
}
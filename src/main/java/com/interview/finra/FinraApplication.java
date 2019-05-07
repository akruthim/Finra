package com.interview.finra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class FinraApplication {

    private static final Logger logger = LoggerFactory.getLogger(FinraApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FinraApplication.class, args);
    }

    @Value("${file.persist.location}")
    private String fileUploadDirectory;

    /**
     * This Post construct is a convenient operation i am including it here to create the upload directory.
     * This creates the upload directory of your choice, so that you need not have to explicitly create one
     * <p>
     * As a best practice i have configured upload directory as a property.
     */
    @PostConstruct
    public void verifyUploadDirectory() {
        if (!Files.exists(Paths.get(fileUploadDirectory))) {
            try {
                Files.createDirectory(Paths.get(fileUploadDirectory));
            } catch (IOException e) {
                logger.error("Exception occurred while creating upload directory");
            }
        }
    }
}

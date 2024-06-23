package com.example.email_sender_service;


import com.example.email_sender_service.Services.EmailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class EmailTestSender {

    @Autowired
    private EmailService emailService;

    @Test
    void emailSendTest(){
        System.out.println("Sending Email");
        emailService.sendEmail("yash.zilpilwar@pwc.com",
                "Email from spring boot test",
                "This service is developed by Jashan Preet Singh in Spring Boot");
    }

    @Test
    void emailSendToManyTest(){
        System.out.println("Sending Email to All");
        emailService.sendEmailToMany(new String[]{"jashanpreet0100@gmail.com", "deeps3711@gmail.com", "yashzilpilwar@gmail.com"},
                "Email from spring boot test to Many sent by Jashan",
                "This service is developed by Jashan Preet Singh in Spring Boot to Test sending multiple emails");
    }

    @Test
    void sendEmailWithFile() throws MessagingException {
        System.out.println("Sending Email to All");
        try {
            emailService.sendEmailWithFile("jashanpreet0100@gmail.com",
                    "Email from spring boot test with file",
                    "This service is developed by Jashan Preet Singh in Spring Boot test with file",
                    new File("C:/Users/Jashan/Desktop/file-test"));
        } catch (Exception e){
            System.out.println("File Path not found");
        }
    }

    @Test
    void sendHtmlTest(){
        String html = "" +
                "<h1 style='color:blue;border:5px solid red;'>This Service is build by Yash Zilpilwar</h1>" +
                "";
        emailService.sendEmailWithHTML("yash.zilpilwar@pwc.com",
                "Email from spring boot with html",
                html);
    }
}

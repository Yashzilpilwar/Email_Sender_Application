package com.example.email_sender_service.Services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class EmailServiceImplementation implements EmailService {

    //Automatic Java Class
    private JavaMailSender mailSender;



    public EmailServiceImplementation(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("yashzilpilwar@gmail.com");
        mailSender.send(simpleMailMessage);
        //Informational Log

    }

    @Override
    public void sendEmailToMany(String[] to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("yashzilpilwar@gmail.com");
        mailSender.send(simpleMailMessage);
        //Informational Log

    }

    @Override
    public void sendEmailWithHTML(String to, String subject, String htmlContent) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("yashzilpilwar@gmail.com");
            helper.setText(htmlContent,true);
            mailSender.send(mimeMessage);

        } catch (MessagingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom("yashzilpilwar@gmail.com");
            helper.setTo(to);
            helper.setText(message);
            helper.setSubject(subject);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()),file);
            mailSender.send(mimeMessage);

        } catch (MessagingException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendEmailWithStream(String to, String subject, String message, InputStream inputStream) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom("yashzilpilwar@gmail.com");
            helper.setTo(to);
            helper.setText(message,true);
            helper.setSubject(subject);
            File file = new File("test.png");
            Files.copy(inputStream,file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(),file);
            mailSender.send(mimeMessage);

        } catch (MessagingException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

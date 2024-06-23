package com.example.email_sender_service.Controller;


import com.example.email_sender_service.Dtos.EmailRequestDto;
import com.example.email_sender_service.Services.EmailService;
import com.example.email_sender_service.helper.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

    private EmailService emailService;
    public EmailSenderController(EmailService emailService) {
        this.emailService = emailService;
    }
    //send email
    @PostMapping("/sendWithHtml")
    public ResponseEntity<CustomResponse> sendEmail(@RequestBody EmailRequestDto emailrequestDto){
        emailService.sendEmailWithHTML(emailrequestDto.getTo(),
                emailrequestDto.getSubject(),
                emailrequestDto.getMessage());
        return ResponseEntity.ok(
                CustomResponse.builder().message("Email Sent Sucessfully ...... ").
                        httpStatus(HttpStatus.OK).success(true).build()
        );
    }

    @PostMapping("/sendWithFile")
    public ResponseEntity<CustomResponse> sendEmailWithFile(@RequestPart EmailRequestDto emailrequestDto,
                                                            @RequestPart MultipartFile file) throws IOException {
        emailService.sendEmailWithStream(emailrequestDto.getTo(),
                emailrequestDto.getSubject(),
                emailrequestDto.getMessage(),
                file.getInputStream());
        return ResponseEntity.ok(
                CustomResponse.builder().message("Email Sent Sucessfully ...... ").
                        httpStatus(HttpStatus.OK).success(true).build()
        );
    }

}

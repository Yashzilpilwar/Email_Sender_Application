package com.example.email_sender_service.helper;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomResponse {
    private String message;
    private HttpStatus httpStatus;
    private boolean success = false;
}

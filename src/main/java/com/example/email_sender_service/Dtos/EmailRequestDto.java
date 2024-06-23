package com.example.email_sender_service.Dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailRequestDto {

    private String to;
    private String subject;
    private String message;
}

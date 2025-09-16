package com.example.superAdmin.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SmsRequestDTO {
    private String providerName;
    private String apiKey;
    private String senderId;
}

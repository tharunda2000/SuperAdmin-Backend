package com.example.superAdmin.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SmsResponseDTO {
    private int id;
    private String providerName;
    private String senderId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}

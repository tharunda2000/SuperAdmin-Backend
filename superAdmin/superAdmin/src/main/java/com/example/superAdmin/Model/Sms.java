package com.example.superAdmin.Model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Sms {
    private int id;
    private String providerName;
    private String apiKey;
    private String senderId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

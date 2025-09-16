package com.example.superAdmin.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EmailResponseDTO {
    private int id;
    private String smtpHost;
    private int smtpPort;
    private String username;
    private String fromAddress;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

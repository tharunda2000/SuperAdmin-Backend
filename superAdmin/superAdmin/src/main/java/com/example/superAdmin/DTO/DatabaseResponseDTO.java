package com.example.superAdmin.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DatabaseResponseDTO {
    private int id;
    private String host;
    private int port;
    private String databaseName;
    private String username;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}


package com.example.superAdmin.Model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Email {
    private int id;
    private String smtpHost;
    private int smtpPort;
    private String username;
    private String password;
    private String fromAddress;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

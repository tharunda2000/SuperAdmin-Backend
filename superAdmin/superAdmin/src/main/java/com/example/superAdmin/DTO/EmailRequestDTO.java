package com.example.superAdmin.DTO;

import lombok.Data;


@Data
public class EmailRequestDTO {
    private String smtpHost;
    private int smtpPort;
    private String username;
    private String password;
    private String fromAddress;
}

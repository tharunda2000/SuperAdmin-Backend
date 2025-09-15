package com.example.superAdmin.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data

public class DatabaseRequestDTO {

    private String host;
    private int port;
    private String databaseName;
    private String username;
    private String password;

}

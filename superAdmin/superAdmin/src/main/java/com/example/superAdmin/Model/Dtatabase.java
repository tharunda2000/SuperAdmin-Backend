package com.example.superAdmin.Model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class Dtatabase {
    private int id;
    private String host;
    private int port;
    private String databaseName;
    private String username;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

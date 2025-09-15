package com.example.superAdmin.Sercice;

import com.example.superAdmin.Repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    DatabaseRepository dataRepo;


}

package com.example.superAdmin.Sercice;

import com.example.superAdmin.Repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Autowired
    SmsRepository smsRepo;
}

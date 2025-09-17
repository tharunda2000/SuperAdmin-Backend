package com.example.superAdmin.Controller;

import com.example.superAdmin.Sercice.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    @Autowired
    SmsService service;
}

package com.example.superAdmin.Controller;

import com.example.superAdmin.DTO.DatabaseRequestDTO;
import com.example.superAdmin.DTO.DatabaseResponseDTO;
import com.example.superAdmin.DTO.SmsRequestDTO;
import com.example.superAdmin.DTO.SmsResponseDTO;
import com.example.superAdmin.Model.Sms;
import com.example.superAdmin.Sercice.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/database-settings")
public class SmsController {

    @Autowired
    SmsService service;

    @PostMapping
    public ResponseEntity<String> addDatabase(@RequestBody SmsRequestDTO reqDTO){
        service.addSms(reqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("SMS settings added Successfully");
    }

    @GetMapping
    public List<SmsResponseDTO> getAllDatabases(){
        return service.getAllSms();
    }

    @GetMapping("/{id}")
    public SmsResponseDTO getSmsById(@PathVariable int id){
        return service.getSmsById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSms(@RequestBody SmsRequestDTO req,@PathVariable int id){
        service.updateSms(req,id);
        return ResponseEntity.status(HttpStatus.OK).body("SMS settings Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSmsById(@PathVariable int id){
        service.deleteSmsById(id);
        return ResponseEntity.status(HttpStatus.OK).body("SMS settings Deleted Successfully");
    }
}

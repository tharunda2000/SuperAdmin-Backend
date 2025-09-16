package com.example.superAdmin.Controller;


import com.example.superAdmin.DTO.EmailRequestDTO;
import com.example.superAdmin.DTO.EmailResponseDTO;
import com.example.superAdmin.Sercice.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email-settings")
public class EmailController {

    @Autowired
    EmailService service;

    @PostMapping
    public ResponseEntity<String> addEmail(@RequestBody EmailRequestDTO reqDTO){
        service.addEmail(reqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Email settings added Successfully");
    }

    @GetMapping
    public List<EmailResponseDTO> getAllEmails(){
        return service.getAllEmails();
    }

    @GetMapping("/{id}")
    public EmailResponseDTO getEmailById(@PathVariable int id){
        return service.getEmailById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmail(@RequestBody EmailRequestDTO req,@PathVariable int id){
        service.updateEmail(req,id);
        return ResponseEntity.status(HttpStatus.OK).body("Email settings Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmailById(@PathVariable int id){
        service.deleteEmailById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Email settings Deleted Successfully");
    }
}

package com.example.superAdmin.Sercice;

import com.example.superAdmin.DTO.DatabaseRequestDTO;
import com.example.superAdmin.DTO.DatabaseResponseDTO;

import com.example.superAdmin.DTO.EmailRequestDTO;
import com.example.superAdmin.DTO.EmailResponseDTO;
import com.example.superAdmin.Model.Database;
import com.example.superAdmin.Model.Email;
import com.example.superAdmin.Repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepo;

    //Convert Request DTO to Entity
    public Email mapToEntity(EmailRequestDTO reqDto){
        Email email = new Email();
        email.setSmtpHost(reqDto.getSmtpHost());
        email.setSmtpPort(reqDto.getSmtpPort());
        email.setUsername(reqDto.getUsername());
        email.setPassword(reqDto.getPassword());
        email.setFromAddress(reqDto.getFromAddress());
        return email;
    }

    //Convert Entity to Response DTO
    public EmailResponseDTO mapToResponse(Email email){
        EmailResponseDTO response = new EmailResponseDTO();
        response.setId(email.getId());
        response.setSmtpHost(email.getSmtpHost());
        response.setSmtpPort(email.getSmtpPort());
        response.setFromAddress(email.getFromAddress());
        response.setUsername(email.getUsername());
        response.setUpdatedAt(email.getUpdatedAt());
        response.setCreatedAt(email.getCreatedAt());

        return response;
    }

    //Add Email settings
    public void addEmail(EmailRequestDTO reqDTO){
        emailRepo.addEmail(mapToEntity(reqDTO));
    }

    //Get all Email settings
    public List<EmailResponseDTO> getAllEmails(){
        return emailRepo.getAllEmails()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //Get Email by id
    public EmailResponseDTO getEmailById(int id){
        return mapToResponse(emailRepo.getEmailById(id));
    }

    //Update Email settings
    public void updateEmail(EmailRequestDTO req,int id){
        Email data = mapToEntity(req);
        data.setId(id);
        emailRepo.updateEmail(data);
    }

    //Delete email
    public void deleteEmailById(int id){
        emailRepo.deleteEmailById(id);
    }
}

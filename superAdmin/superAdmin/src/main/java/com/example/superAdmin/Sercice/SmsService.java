package com.example.superAdmin.Sercice;

import com.example.superAdmin.DTO.SmsRequestDTO;
import com.example.superAdmin.DTO.SmsResponseDTO;
import com.example.superAdmin.Model.Sms;
import com.example.superAdmin.Repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmsService {

    @Autowired
    SmsRepository smsRepo;

    //Convert Request DTO to Entity
    public Sms mapToEntity(SmsRequestDTO reqDto){
        Sms sms = new Sms();
        sms.setProviderName(reqDto.getProviderName());
        sms.setApiKey(reqDto.getApiKey());
        sms.setSenderId(reqDto.getSenderId());

        return sms;
    }

    //Convert Entity to Response DTO
    public SmsResponseDTO mapToResponse(Sms sms){
        SmsResponseDTO response = new SmsResponseDTO();
        response.setId(sms.getId());
        response.setProviderName(sms.getProviderName());
        response.setSenderId(sms.getSenderId());
        response.setUpdatedAt(sms.getUpdatedAt());
        response.setCreatedAt(sms.getCreatedAt());

        return response;
    }

    //Add a SMS settings
    public void addSms(SmsRequestDTO reqDTO){
        smsRepo.addSms(mapToEntity(reqDTO));
    }

    //Get all SMS settings
    public List<SmsResponseDTO> getAllSms(){
        return smsRepo.getAllSms()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    //Get SMS settings by id
    public SmsResponseDTO getSmsById(int id){
        return mapToResponse(smsRepo.getSmsById(id));
    }

    //Update SMS settings
    public void updateSms(SmsRequestDTO req,int id){
        Sms sms = mapToEntity(req);
        sms.setId(id);
        smsRepo.updateSms(sms);
    }

    //Delete SMS settings
    public void deleteSmsById(int id){
        smsRepo.deleteSmsById(id);
    }

}

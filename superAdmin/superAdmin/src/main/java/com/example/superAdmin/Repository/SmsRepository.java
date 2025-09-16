package com.example.superAdmin.Repository;


import com.example.superAdmin.Model.Database;
import com.example.superAdmin.Model.Sms;

import java.util.List;

public interface SmsRepository {
    void addSms(Sms sms);
    List<Sms> getAllSms();
    Sms getSmsById(int id);
    void updateSms(Sms sms);
    void deleteSmsById(int id);
}

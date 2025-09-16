package com.example.superAdmin.Repository;

import com.example.superAdmin.Model.Email;

import java.util.List;

public interface EmailRepository {
    void addEmail(Email email);
    List<Email> getAllEmails();
    Email getEmailById(int id);
    void updateEmail(Email email);
    void deleteEmailById(int id);
}

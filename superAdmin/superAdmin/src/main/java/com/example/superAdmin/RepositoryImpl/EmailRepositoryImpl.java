package com.example.superAdmin.RepositoryImpl;


import com.example.superAdmin.Model.Email;
import com.example.superAdmin.Repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class EmailRepositoryImpl implements EmailRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addEmail(Email email){
       try{
           String insertQuery = "INSERT INTO email (smtp_host, smtp_port, username, password, from_address, created_at, updated_at) " +
                   "VALUES (?, ?, ?, ?, ?, ?, ?)";
           jdbcTemplate.update(insertQuery,
                   email.getSmtpHost(),
                   email.getSmtpPort(),
                   email.getUsername(),
                   email.getPassword(),
                   email.getFromAddress(),
                   new Timestamp(System.currentTimeMillis()),
                   new Timestamp(System.currentTimeMillis())
           );
       }catch(Exception e){
           throw new RuntimeException("Failed to add the email settings : "+e.getMessage() ,e);
       }
    }

    @Override
    public List<Email> getAllEmails(){
        try{
            String selectAllQuery = "SELECT id, smtp_host AS smtpHost, smtp_port AS smtpPort, username, password,from_address AS fromAddress, created_at AS createdAt,updated_at AS updatedAt FROM `email`";
            return jdbcTemplate.query(selectAllQuery,new BeanPropertyRowMapper<>(Email.class));
        }catch(Exception e){
            throw new RuntimeException("Failed to fetch Email settings: " + e.getMessage(), e);
        }
    }

    @Override
    public Email getEmailById(int id){
        try{
            String selectByIdQuery = "SELECT id, smtp_host AS smtpHost, smtp_port AS smtpPort, username, password,from_address AS fromAddress, created_at AS createdAt,updated_at AS updatedAt FROM `email` WHERE id=?";
            return jdbcTemplate.queryForObject(selectByIdQuery,BeanPropertyRowMapper.newInstance(Email.class),id);
        }catch(EmptyResultDataAccessException e){
            throw new RuntimeException("Email settings with ID " + id + " not found");
        }catch(Exception e){
            throw new RuntimeException("Failed to fetch Email settings : " + e.getMessage(), e);
        }
    }

    @Override
    public void updateEmail(Email email){
        try{
            String updateQuery = "UPDATE `email` SET  smtp_host=?, smtp_port=?,  username=?, password=?,from_address=?, updated_at=NOW() WHERE   id=?";
            int rowsAffected = jdbcTemplate.update(updateQuery,
                    email.getSmtpHost(),
                    email.getSmtpPort(),
                    email.getUsername(),
                    email.getPassword(),
                    email.getFromAddress(),
                    email.getId()
            );

            if(rowsAffected==0){
                throw new RuntimeException("No Email settings found with ID "+email.getId()+"to update");
            }

        }catch(Exception e){
            throw new RuntimeException("Failed to update the Email settings" +e.getMessage(),e);
        }

    }

    @Override
    public void deleteEmailById(int id){
        try{
            String deleteByIdQuery ="DELETE FROM `email` WHERE id=?";
            int rowsAffected = jdbcTemplate.update(deleteByIdQuery,id);

            if(rowsAffected==0){
                throw new RuntimeException("No Database settings found with ID "+id+"to Delete");
            }
        }catch(Exception e){
            throw new RuntimeException(" Failed to delete Email settings"+e.getMessage(),e);
        }
    }

}

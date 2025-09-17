package com.example.superAdmin.RepositoryImpl;

import com.example.superAdmin.Model.Database;
import com.example.superAdmin.Model.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class SmsRepositoryImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addSms(Sms sms){
        try{
            String insertQuery = "INSERT INTO `sms` (provider_name,api_key,sender_id,created_at,updated_at)"+
                    "VALUES (?,?,?,?,?)";

            jdbcTemplate.update(insertQuery,
                    sms.getProviderName(),
                    sms.getApiKey(),
                    sms.getSenderId(),
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            );
        }catch(Exception e){
            throw new RuntimeException("Failed to add sms settings : "+e.getMessage() ,e);

        }
    }


    public List<Sms> getAllSms(){
        try{
            String selectAllQuery = "SELECT id, provider_name AS providerName, api_key AS apiKey, sender_id AS senderId, created_at AS createdAt,updated_at AS updatedAt FROM `sms`";
            return jdbcTemplate.query(selectAllQuery,new BeanPropertyRowMapper<>(Sms.class));
        }catch(Exception e){
            throw new RuntimeException("Failed to fetch sms settings: " + e.getMessage(), e);
        }
    }


    public Sms getSmsById(int id){
        try{
            String selectByIdQuery = "SELECT id, provider_name AS providerName, api_key AS apiKey, sender_id AS senderId, created_at AS createdAt,updated_at AS updatedAt FROM `sms` WHERE id=?";
            return jdbcTemplate.queryForObject(selectByIdQuery,BeanPropertyRowMapper.newInstance(Sms.class),id);
        }catch(EmptyResultDataAccessException e){
            throw new RuntimeException("Sms settings with ID " + id + " not found");
        }catch(Exception e){
            throw new RuntimeException("Failed to fetch sms settings: " + e.getMessage(), e);
        }
    }

    public void updateSms(Sms sms){
        try{
            String updateQuery = "UPDATE `sms` SET  provider_name=?, api_key=?, sender_id=?, updated_at=NOW() WHERE   id=?";
            int rowsAffected = jdbcTemplate.update(updateQuery,
                    sms.getProviderName(),
                    sms.getApiKey(),
                    sms.getSenderId(),
                    sms.getId()
            );

            if(rowsAffected==0){
                throw new RuntimeException("No sms settings are found with ID "+sms.getId()+"to update");
            }

        }catch(Exception e){
            throw new RuntimeException("Failed to update sms settings" +e.getMessage(),e);
        }
    }

    public void deleteSmsById(int id){
        try{
            String deleteByIdQuery ="DELETE FROM `sms` WHERE id=?";
            int rowsAffected = jdbcTemplate.update(deleteByIdQuery,id);

            if(rowsAffected==0){
                throw new RuntimeException("No sms settings are found with ID "+id+"to Delete");
            }
        }catch(Exception e){
            throw new RuntimeException(" Failed to delete sms settings"+e.getMessage(),e);
        }

    }
}

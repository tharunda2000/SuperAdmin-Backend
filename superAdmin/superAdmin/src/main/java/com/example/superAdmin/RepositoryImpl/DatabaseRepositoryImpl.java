package com.example.superAdmin.RepositoryImpl;

import com.example.superAdmin.Model.Database;
import com.example.superAdmin.Repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class DatabaseRepositoryImpl implements DatabaseRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addDatabase(Database database){

        String insertQuery = "INSERT INTO databases (host,port,database_name,username,password,created_at,updated_at)"+
                "VALUES (?,?,?,?,?,?,?)";

        jdbcTemplate.update(insertQuery,
                database.getHost(),
                database.getPort(),
                database.getDatabaseName(),
                database.getUsername(),
                database.getPassword(),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
    }

    @Override
    public List<Database> getAllDatabases(){
        String selectAllQuery = "SELECT id, host, port, database_name AS databaseName, username, password, created_at AS createdAt,updated_at AS updatedAt FROM databases";
        return jdbcTemplate.query(selectAllQuery,new BeanPropertyRowMapper<>(Database.class));
    }

    @Override
    public Database getDatabaseById(int id){
        String selectByIdQuery = "SELECT id, host, port, database_name AS databaseName, username, password, created_at AS createdAt,updated_at AS updatedAt FROM databases WHERE id=?";
        return jdbcTemplate.queryForObject(selectByIdQuery,BeanPropertyRowMapper.newInstance(Database.class),id);
    }

    @Override
    public void updateDatabase(Database database){
        String updateQuery = "UPDATE databases SET  host=?, port=?, database_name=?, username=?, password=?, updated_at WHERE   id=?";
        jdbcTemplate.update(updateQuery,
                database.getHost(),
                database.getPort(),
                database.getDatabaseName(),
                database.getUsername(),
                database.getPassword(),
                database.getUpdatedAt(),
                database.getId()
        );
    }

    @Override
    public void deleteDatabaseById(int id){
        String deleteByIdQuery ="DELETE FROM databases WHERE id=?";
        jdbcTemplate.update(deleteByIdQuery,id);
    }
}





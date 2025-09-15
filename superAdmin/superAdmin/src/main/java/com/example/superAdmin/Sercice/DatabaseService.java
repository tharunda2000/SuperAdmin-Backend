package com.example.superAdmin.Sercice;

import com.example.superAdmin.DTO.DatabaseRequestDTO;
import com.example.superAdmin.DTO.DatabaseResponseDTO;
import com.example.superAdmin.Model.Database;
import com.example.superAdmin.Repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseService {

    @Autowired
    DatabaseRepository dataRepo;

    //Convert Request DTO to Entity
    public Database mapToEntity(DatabaseRequestDTO reqDto){
        Database database = new Database();
        database.setHost(reqDto.getHost());
        database.setPort(reqDto.getPort());
        database.setDatabaseName(reqDto.getDatabaseName());
        database.setPassword(reqDto.getPassword());
        database.setUsername(reqDto.getUsername());

        return database;
    }

    //Convert Entity to Response DTO
    public DatabaseResponseDTO mapToResponse(Database database){
        DatabaseResponseDTO response = new DatabaseResponseDTO();
        response.setId(database.getId());
        response.setHost(database.getHost());
        response.setPort(database.getPort());
        response.setDatabaseName(database.getDatabaseName());
        response.setUsername(database.getUsername());
        response.setUpdatedAt(database.getUpdatedAt());
        response.setCreatedAt(database.getCreatedAt());

        return response;
    }

    public void addDatabase(DatabaseRequestDTO reqDTO){
        dataRepo.addDatabase(mapToEntity(reqDTO));
    }

    public List<DatabaseResponseDTO> getAllDatabases(){
        return dataRepo.getAllDatabases()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public DatabaseResponseDTO getDatabaseById(int id){
        return mapToResponse(dataRepo.getDatabaseById(id));
    }

    public void updateDatabase(DatabaseRequestDTO req){
        dataRepo.updateDatabase(mapToEntity(req));
    }

    public void deleteDatabaseById(int id){
        dataRepo.deleteDatabaseById(id);
    }

}

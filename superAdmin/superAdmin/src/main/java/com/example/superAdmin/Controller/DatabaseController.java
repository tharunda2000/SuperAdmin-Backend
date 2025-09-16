package com.example.superAdmin.Controller;

import com.example.superAdmin.DTO.DatabaseRequestDTO;
import com.example.superAdmin.DTO.DatabaseResponseDTO;
import com.example.superAdmin.Sercice.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/database-settings")
public class DatabaseController {

    @Autowired
    DatabaseService service;

    @PostMapping
    public ResponseEntity<String> addDatabase(@RequestBody DatabaseRequestDTO reqDTO){
        service.addDatabase(reqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Database settings added Successfully");
    }

    @GetMapping
    public List<DatabaseResponseDTO> getAllDatabases(){
        return service.getAllDatabases();
    }

    @GetMapping("/{id}")
    public DatabaseResponseDTO getDatabaseById(@PathVariable int id){
        return service.getDatabaseById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDatabase(@RequestBody DatabaseRequestDTO req,@PathVariable int id){
        service.updateDatabase(req,id);
        return ResponseEntity.status(HttpStatus.OK).body("Database Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDatabaseById(@PathVariable int id){
        service.deleteDatabaseById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Database Deleted Successfully");
    }
}

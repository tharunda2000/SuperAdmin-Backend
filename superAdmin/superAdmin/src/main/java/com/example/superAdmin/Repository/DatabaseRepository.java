package com.example.superAdmin.Repository;

import com.example.superAdmin.Model.Database;

import java.util.List;

public interface DatabaseRepository {

        void addDatabase(Database database);
        List<Database> getAllDatabases();
        Database getDatabaseById(int id);
        void updateDatabase(Database database);
        void deleteDatabaseById(int id);

}

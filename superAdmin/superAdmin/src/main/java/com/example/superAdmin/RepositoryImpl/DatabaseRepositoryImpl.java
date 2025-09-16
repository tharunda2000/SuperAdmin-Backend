    package com.example.superAdmin.RepositoryImpl;

    import com.example.superAdmin.Model.Database;
    import com.example.superAdmin.Repository.DatabaseRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.dao.EmptyResultDataAccessException;
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

            try{
                String insertQuery = "INSERT INTO `databases` (host,port,database_name,username,password,created_at,updated_at)"+
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
            }catch(Exception e){
                throw new RuntimeException("Failed to add the database : "+e.getMessage() ,e);

            }
        }

        @Override
        public List<Database> getAllDatabases(){
            try{
                String selectAllQuery = "SELECT id, host, port, database_name AS databaseName, username, password, created_at AS createdAt,updated_at AS updatedAt FROM `databases`";
                return jdbcTemplate.query(selectAllQuery,new BeanPropertyRowMapper<>(Database.class));
            }catch(Exception e){
                throw new RuntimeException("Failed to fetch databases: " + e.getMessage(), e);
            }
        }

        @Override
        public Database getDatabaseById(int id){
            try{
                String selectByIdQuery = "SELECT id, host, port, database_name AS databaseName, username, password, created_at AS createdAt,updated_at AS updatedAt FROM `databases` WHERE id=?";
                return jdbcTemplate.queryForObject(selectByIdQuery,BeanPropertyRowMapper.newInstance(Database.class),id);
            }catch(EmptyResultDataAccessException e){
                throw new RuntimeException("Database with ID " + id + " not found");
            }catch(Exception e){
                throw new RuntimeException("Failed to fetch databases: " + e.getMessage(), e);
            }
        }

        @Override
        public void updateDatabase(Database database){
            try{
                String updateQuery = "UPDATE `databases` SET  host=?, port=?, database_name=?, username=?, password=?, updated_at=NOW() WHERE   id=?";
                int rowsAffected = jdbcTemplate.update(updateQuery,
                        database.getHost(),
                        database.getPort(),
                        database.getDatabaseName(),
                        database.getUsername(),
                        database.getPassword(),
                        database.getId()
                );

                if(rowsAffected==0){
                    throw new RuntimeException("No database found with ID "+database.getId()+"to update");
                }

            }catch(Exception e){
                throw new RuntimeException("Failed to update the database" +e.getMessage(),e);
            }
        }

        @Override
        public void deleteDatabaseById(int id){
            try{
                String deleteByIdQuery ="DELETE FROM `databases` WHERE id=?";
                int rowsAffected = jdbcTemplate.update(deleteByIdQuery,id);

                if(rowsAffected==0){
                    throw new RuntimeException("No database found with ID "+id+"to Delete");
                }
            }catch(Exception e){
                throw new RuntimeException(" Failed to delete database"+e.getMessage(),e);
            }
        }
    }





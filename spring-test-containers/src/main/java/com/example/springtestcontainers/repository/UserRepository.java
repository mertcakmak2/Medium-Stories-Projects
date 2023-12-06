package com.example.springtestcontainers.repository;

import com.example.springtestcontainers.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query("FROM User u WHERE u.name = ?1")
    List<User> getUserByName(@Param("name") String name);
}

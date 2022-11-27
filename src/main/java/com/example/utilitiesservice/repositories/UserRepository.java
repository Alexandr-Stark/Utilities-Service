package com.example.utilitiesservice.repositories;

import com.example.utilitiesservice.models.Residence;
import com.example.utilitiesservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users where email = ?1", nativeQuery = true)
    User findByEmail(String email);

    @Modifying
    @Query(
            value = "INSERT INTO users (user_id, name, surname, middle_name, email, password, phone, role_id)" +
                    "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8);",
            nativeQuery = true)
    @Transactional
   int create (Long id, String name, String surname, String middleName, String email, String pass, String ph, Long roleId);

    ;
}

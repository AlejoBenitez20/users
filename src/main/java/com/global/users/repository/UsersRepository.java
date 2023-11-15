package com.global.users.repository;

import com.global.users.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserModel, String> {

    @Query("SELECT c FROM UserModel c WHERE c.email = ?1")
    Optional<UserModel> findByEmail(String email);

}

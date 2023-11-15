package com.global.users.repository;

import com.global.users.model.PhoneModel;
import com.global.users.model.UserModel;
import org.hibernate.type.UUIDCharType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PhonesRepository extends JpaRepository<PhoneModel, UUID> {

    @Query("SELECT c FROM PhoneModel c WHERE c.userId = ?1")
    Optional<List<PhoneModel>> findByUserId(UUID userId);

}

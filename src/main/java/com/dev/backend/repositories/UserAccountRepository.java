package com.dev.backend.repositories;

import com.dev.backend.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    UserAccount findByEmail(String email);

}
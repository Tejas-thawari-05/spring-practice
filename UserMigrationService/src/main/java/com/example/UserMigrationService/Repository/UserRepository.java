package com.example.UserMigrationService.Repository;

import com.example.UserMigrationService.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

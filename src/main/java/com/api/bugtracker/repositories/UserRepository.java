package com.api.bugtracker.repositories;

import com.api.bugtracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(String login);
    User findUserById(String id);

}

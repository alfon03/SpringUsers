package com.user.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer	> {

}

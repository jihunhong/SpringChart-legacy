package com.cafe24.demo.DAO;

import com.cafe24.demo.VO.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
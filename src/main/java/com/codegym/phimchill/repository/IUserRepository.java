package com.codegym.phimchill.repository;
import com.codegym.phimchill.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
    User findByName(String name);
}

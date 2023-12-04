package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
    User findByName(String name);

    User findUsersById(Long userId);

}

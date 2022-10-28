package com.demo.myapp.repository;

import com.demo.myapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Modifying
    @Query(value = "update User u set u.role = 'Admin' where u.user_id = ?1", nativeQuery = true)
    void setUserInfoById(int user_id);
    User findByEmail(String email);
}

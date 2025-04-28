package com.example.bankmanagementsystem.Repository;

import com.example.bankmanagementsystem.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<MyUser, Integer> {

    MyUser findUserByUsername(String username);


}
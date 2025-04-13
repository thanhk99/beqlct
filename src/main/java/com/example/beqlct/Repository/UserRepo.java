package com.example.beqlct.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.beqlct.Model.userModel;


@Repository
public interface UserRepo extends JpaRepository<userModel,Integer> { 
    Optional<userModel> findByAccount(String account);
}

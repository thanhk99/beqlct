package com.example.beqlct.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.beqlct.Model.wallet;
@Repository
public interface walletRepo extends JpaRepository<wallet, Integer> {
    List<wallet> findByIdUser(int userid);
}

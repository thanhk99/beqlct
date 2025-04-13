package com.example.beqlct.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beqlct.Model.userModel;
import com.example.beqlct.Model.wallet;
import com.example.beqlct.Repository.UserRepo;
import com.example.beqlct.Repository.walletRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired 
    private walletRepo walletRepo;
    @PostMapping("/login")
    public ResponseEntity<?> postMethodName(@RequestBody userModel entity) {
        Optional<userModel> user = userRepo.findByAccount(entity.getAccount());
        if(user.isPresent() && user.get().getPassword().equals(entity.getPassword())){
            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai tài khoản hoặc mật khẩu");
        }
    }
    @PostMapping("/wallet")
    public ResponseEntity<?> getWallet(@RequestBody wallet entity) {
        List<wallet> wallet= walletRepo.findByIdUser(entity.getIdUser());
        return ResponseEntity.ok(wallet);
    }

}

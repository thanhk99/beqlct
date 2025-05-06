package com.example.beqlct.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beqlct.Model.wallet;
import com.example.beqlct.Repository.walletRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("wallet")
public class WalletController {
    @Autowired 
    private walletRepo walletRepo;

    @PostMapping("/create")
    public ResponseEntity<?> createWallet(@RequestBody wallet entity) {
        walletRepo.save(entity);
        return ResponseEntity.ok(entity);
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteWallet(@RequestBody wallet entity) {
        walletRepo.deleteById(entity.getId());
        return ResponseEntity.ok(entity);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateWallet(@RequestBody wallet entity) {
        if (walletRepo.existsById(entity.getId())) {
            walletRepo.save(entity);
            return ResponseEntity.ok(entity);
        } else {
            ResponseEntity.ok(entity.getId());
        }
        return null;
    }
    @PostMapping("updateBalance")
    public ResponseEntity<?> updateBalance(@RequestBody wallet entity) {
        Optional<wallet> walletInfo = walletRepo.findById(entity.getId());
        if(walletInfo.isPresent()){
            wallet wallet = walletInfo.get();
            wallet.setBalance(wallet.getBalance() + entity.getBalance());
            walletRepo.save(wallet);
            return ResponseEntity.ok(wallet);
        }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy Wallet: " + entity.getId());
        }
    }
    public void updateBalanceService(int id,float balance){
        Optional<wallet> walletInfo = walletRepo.findById(id);
        if(walletInfo.isPresent()){
            wallet wallet = walletInfo.get();
            wallet.setBalance(wallet.getBalance() + balance);
            walletRepo.save(wallet);
        }else {
            System.out.println( "Không tìm thấy Wallet: " + id);
        }
    }
    @PostMapping("getWallet")
    public ResponseEntity<?> getWallet(@RequestBody wallet entity) {
        Optional<wallet> wallet = walletRepo.findById(entity.getId());
        return ResponseEntity.ok(wallet);
    }
    
    
}

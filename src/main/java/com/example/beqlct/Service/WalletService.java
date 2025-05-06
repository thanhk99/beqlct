package com.example.beqlct.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.beqlct.Model.wallet;
import com.example.beqlct.Repository.walletRepo;

@Service
public class WalletService {

    @Autowired
    private walletRepo walletRepo;

    public void updateWalletBalance(int walletId, float amountChange) {
        wallet wallet = walletRepo.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ví"));
        wallet.setBalance(wallet.getBalance() + amountChange);
        System.out.println(amountChange);
        walletRepo.save(wallet);
    }
}
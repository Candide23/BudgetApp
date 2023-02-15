package com.manageBudget.demo.Service;

import com.manageBudget.demo.Entity.Wallet;
import com.manageBudget.demo.Exceptions.WalletException;
import com.manageBudget.demo.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> getAll() {
        return walletRepository.findAllByOrderByPriority();
    }



    public Wallet getById(Long id) {

        Optional<Wallet> wallet = walletRepository.findById(id);

        if(wallet.isPresent()) {
            walletRepository.delete(wallet.get());
            return wallet.get();
        }


        throw new WalletException("Wallet with " +id+" does not exist");

    }

    public Wallet createOrUpdate (Wallet wallet) {

        if(wallet.getId() ==0) {
            walletRepository.save(wallet);
        }else {
            walletRepository.save(wallet);
        }

        return wallet;

    }

    public boolean delete(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);

        if(wallet.isPresent()) {
            walletRepository.delete(wallet.get());
            return true;
        }

        throw new WalletException("Wallet with " +id+" does not exist");

    }
}

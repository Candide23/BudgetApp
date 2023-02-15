package com.manageBudget.demo.Controllers;


import com.manageBudget.demo.Entity.Wallet;
import com.manageBudget.demo.Exceptions.WalletException;
import com.manageBudget.demo.Repository.WalletRepository;
import com.manageBudget.demo.Service.ValidationErrorService;
import com.manageBudget.demo.Service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private ValidationErrorService validationService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(walletService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(walletService.getById(id), HttpStatus.OK);
    }



/*@PostMapping: This annotation indicates that the method is used to handle HTTP POST requests.

public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result):
This is the method signature. It takes a Wallet object as a request body and a BindingResult
object to store validation errors.


Wallet walletSaved = walletService.createOrUpdate(wallet);: This line calls the createOrUpdate
method of a walletService object to save the Wallet object to the database.
return new ResponseEntity<Wallet>(walletSaved,HttpStatus.CREATED);: If the object
 is successfully saved, this line returns a Created HTTP response with the saved Wallet object as its body.*/
    @PostMapping
    public ResponseEntity<?> createWallet(@Valid @RequestBody Wallet wallet, BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if(errors != null) return errors;
         Wallet walletSaved =  walletService.createOrUpdate(wallet);
        return new ResponseEntity<Wallet>(walletSaved,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWallet(@PathVariable Long id,@Valid @RequestBody Wallet wallet, BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if(errors != null) return errors;
        wallet.setId(id);
        Wallet walletSaved =  walletService.createOrUpdate(wallet);
        return new ResponseEntity<Wallet>(walletSaved,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWallet(@PathVariable Long id) {
        walletService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

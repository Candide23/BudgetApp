package com.manageBudget.demo.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationErrorService {

    /*This block of code checks whether there are validation errors in
the BindingResult object. If there are, it creates a Map of error messages and returns
a BadRequest HTTP response.*/

    public ResponseEntity<?> validate(BindingResult result) {

        if(result.hasErrors()) {
            Map<String, String> errorsMap = new HashMap<String, String>();
            for(FieldError error: result.getFieldErrors()) {
                errorsMap.put(error.getField(), error.getDefaultMessage());
            }

            return  new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        }

        return null;


    }
}

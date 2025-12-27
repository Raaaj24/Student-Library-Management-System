package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Borrow;
import com.examly.springapp.repository.BorrowRepo;
import com.examly.springapp.service.BorrowService;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {
    @Autowired
    private BorrowService service;
    @GetMapping("/{id}")
    public ResponseEntity<?> getBorrowById(@PathVariable int id ){
        Borrow borrow = service.getBorrowById(id);
        if(borrow==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(borrow);
    }
}

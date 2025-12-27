package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.service.FineService;

@RestController
@RequestMapping("/api/fines")
public class FineController {
    @Autowired
    private FineService service;
    

}

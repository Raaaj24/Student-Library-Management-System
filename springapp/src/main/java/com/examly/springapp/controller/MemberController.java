package com.examly.springapp.controller;

import java.util.List;

//import org.aspectj.apache.bcel.classfile.ExceptionTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Member;
import com.examly.springapp.service.MemberService;

//import lombok.Delegate;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired 
    private MemberService service;

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member){
        try{
            Member mem = service.createMember(member);
            return ResponseEntity.status(201).body(mem);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers(){
        try{
            List<Member> member = service.getAllMembers();
            return ResponseEntity.status(200).body(member);
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable long id){
        Member mem = service.getMemberById(id);
        if(mem==null){
            return ResponseEntity.status(204).body(null);
        }
        return ResponseEntity.status(200).body(mem);}
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable long id , @RequestBody Member member){
        Member mem = service.updateMember(id , member);
        if(mem==null){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(mem);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMemberById(@PathVariable long id){
         Member a = service.deleteMemberById(id);
        if(a==null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).build();
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> getMembersByPhone(@PathVariable String phone){

        List<Member> mem = service.getMembersByPhone(phone);
        if(mem==null || mem.isEmpty()){
             return ResponseEntity.status(204).body("No member found with phone: "+phone);
        }
        return ResponseEntity.status(200).body(mem);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<List<Member>> getMembersByEmail(@PathVariable String email){
        List<Member> mem = service.getMemberByEmail(email);
        return ResponseEntity.status(200).body(mem);
    }



}

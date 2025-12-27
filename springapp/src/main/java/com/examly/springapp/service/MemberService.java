package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Member;
import com.examly.springapp.repository.MemberRepo;

@Service

public class MemberService {

    @Autowired
    private MemberRepo repo;

    public Member createMember(Member member) {
        return repo.save(member);
    }

    public List<Member> getAllMembers() {
        return repo.findAll();
    }

    public Member getMemberById(long id) {
        return repo.findById(id).orElse(null);
    }

    public Member updateMember(long id, Member member) {
        Member mem = repo.findById(id).orElse(null);
        if(mem==null) return null;
       //mem.setMemberId(member.getMemberId());
        mem.setEmail(member.getEmail());
        mem.setName(member.getName());
        mem.setPhone(member.getPhone());
        repo.save(mem);
        return mem;
    }

    public Member deleteMemberById(long id) {
        
        Member mem = repo.findById(id).orElse(null);
        if(mem==null) return null;
        repo.deleteById(id);
        return mem;
    }

    public List<Member> getMembersByPhone(String phone) {
        
        return repo.findByPhone(phone);
    }

    public List<Member> getMemberByEmail(String email) {
        return repo.findByEmail(email);
    }
    
}

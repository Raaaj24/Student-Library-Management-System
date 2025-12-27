package com.examly.springapp.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity

public class Borrow{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowId;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Member member;
    private Date borrowDate;
    private Date returnDate;

    public Borrow(){

    }
    public Long getBorrowId(){
        return borrowId;
    }
    public void setBorrowId(Long borrowId){
        this.borrowId = borrowId;
    }
    public Member getMember(){
        return member;
    }
    public void setMember(Member member){
        this.member = member;
    }
    public Book getBook(){
        return book;
    }
    public void setBook(Book book){
        this.book = book;
    }
    public Date getBorrowDate(){
        return borrowDate;
    }
    public void setBorrowDate(Date borrowDate){
        this.borrowDate = borrowDate;
    }
    public Date getReturnDate(){
        return returnDate;
    }
    public void setReturnDate(Date returnDate){
        this.returnDate = returnDate;
    }
}

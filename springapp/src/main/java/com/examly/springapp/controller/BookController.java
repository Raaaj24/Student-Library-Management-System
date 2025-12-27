package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Book;
import com.examly.springapp.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService service;
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book){
        Book b1 = service.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(b1);
    }
    @GetMapping
    public ResponseEntity<?> selectAllBook(){
        List<Book>book = service.selectAllBook();
        if(book.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(book);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> selectBookById(@PathVariable int id){
        Book b1 = service.selectBookById(id);
        if(b1==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(b1);
    }
    // @GetMapping("/category/{categoryName}")
    // public ResponseEntity<?> getBookByCategory(@PathVariable String categoryName){
    //     Book b1 = service.getBookByCategory(categoryName);
    //     if(b1==null){
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok(b1);
    // }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id , @RequestBody Book book){
        Book b1 = service.updateBook(id,book);
        if(b1==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(b1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id){
        boolean b = service.deleteBook(id);
        if(b){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Book>> getBookByCategoryName(@PathVariable String categoryName){
        List<Book> book = service.getBookByCategoryName(categoryName);
        return ResponseEntity.status(200).body(book);
        
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<?> getByBookTitle(@PathVariable String title){
        List<Book> book = service.getByBookTitle(title);
        if(book==null || book.isEmpty()){
            return ResponseEntity.status(404).body("No book found with title: "+title);
        }
        return ResponseEntity.status(200).body(book);

    }
}  


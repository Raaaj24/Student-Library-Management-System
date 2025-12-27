package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Book;
import com.examly.springapp.model.BookCategory;
import com.examly.springapp.service.BookCategoryService;

@RestController
@RequestMapping("/api/book-categories")
public class BookCategoryController {

    @Autowired
    private BookCategoryService service;

    @PostMapping
    public ResponseEntity<?> createBookCategory(@RequestBody BookCategory bookcategory){
        if(bookcategory==null){
            return ResponseEntity.badRequest().build();
        }
        BookCategory book = service.createBookCategory(bookcategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);

    }

    @GetMapping
    public ResponseEntity<?> selectAllBookCategory(){
        List<BookCategory> list = service.selectAllBookCategory();
        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> selectBookCategoryById(@PathVariable int id){
        BookCategory category = service.selectBookCategoryById(id);
         if(category==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book category not found ");
         }
         return ResponseEntity.ok(category);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id , @RequestBody BookCategory book){
        BookCategory b1 = service.updateBook(id, book);
        if(b1==null){
            return ResponseEntity.status(404).body("Book category not found");
        }
        return ResponseEntity.ok(b1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id){
        BookCategory cat = service.deleteBook(id);
        if(cat!=null)
        return ResponseEntity.ok().body(cat);
    return ResponseEntity.notFound().build();
    }
    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<Page<BookCategory>> getByPagination(@PathVariable int page , @PathVariable int size){
        try{
            Page<BookCategory> cat = service.getByPagination(page,size);
            return ResponseEntity.status(200).body(cat); 
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
}

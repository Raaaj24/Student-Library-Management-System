package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.examly.springapp.model.BookCategory;
import com.examly.springapp.repository.BookCategoryRepo;
@Service

public class BookCategoryService {

    @Autowired
    private BookCategoryRepo repo;

   // @PostMapping

    public BookCategory createBookCategory(BookCategory bookcategory){
         repo.save(bookcategory);
         return bookcategory;
    }

    public List<BookCategory> selectAllBookCategory(){
        return repo.findAll();
    }
    public BookCategory selectBookCategoryById(int id){
        return repo.findById((long)id).orElse(null);
    }
    public BookCategory updateBook(int id , BookCategory book){
        BookCategory existing = repo.findById((long)id).orElse(null);
        if(existing==null){
             return null;
        }
       // existing.setCategoryId(book.getCategoryId());
        existing.setCategoryName(book.getCategoryName());

        return repo.save(existing);
    
    }

    public Page<BookCategory> getByPagination(int page, int size) {
        
        Pageable pageable = PageRequest.of(page,size);
        return repo.findAll(pageable);
    }

    public BookCategory deleteBook(int id) {
        BookCategory b = repo.findById((long)id).orElse(null);
        if(b==null){ return null;
        }
        repo.deleteById((long)id);
        return b;
    }
    
}

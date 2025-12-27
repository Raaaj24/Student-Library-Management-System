package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Book;
import com.examly.springapp.repository.BookRepo;

@Service
public class BookService {
    @Autowired
    private BookRepo repo;

    public Book createBook(Book book) {
        return repo.save(book);
    }

    public List<Book> selectAllBook() {
        return repo.findAll();
    }

    public Book selectBookById(int id) {
        return repo.findById((long)id).orElse(null);
    }

    public Book updateBook(int id, Book book) {
        Book existing = repo.findById((long)id).orElse(null);
        if(existing==null){
            return null;
        }
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setAvailable(book.getAvailable());

        return repo.save(existing);

    }

    public boolean deleteBook(int id) {
        Book existing = repo.findById((long)id).orElse(null);
        if(existing==null){
            return false;
        }
        repo.delete(existing);
        return true;
    }

    // public Book getBookByCategory(String categoryName) {
    //     return repo.findByBookCategory_CategoryName(categoryName);
    // }

    public List<Book> getBookByCategoryName(String categoryName) {
        return repo.findByBookCategory_CategoryName(categoryName);
    }

    public List<Book> getByBookTitle(String title) {
        return repo.findByTitle(title);
    }
}

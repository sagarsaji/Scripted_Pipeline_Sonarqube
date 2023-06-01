package com.example.test.controller;

import com.example.test.model.Book;
import com.example.test.model.User;
import com.example.test.repository.BookRepository;
import com.example.test.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping
public class Librarycontroller {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/addbook")
    public ResponseEntity<Book> create(@RequestBody Book book){
        bookRepository.save(book);
        return ResponseEntity.ok().body(book);
    }
    @PostMapping("/adduser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        Optional<Book> book=bookRepository.findById(id);
        if(book.isPresent()){
            return ResponseEntity.ok().body(bookRepository.findById(id).orElse(null));
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
//        Optional<User> user=userRepository.findById(id);
//        if(user.isPresent()){
//            return ResponseEntity.ok().body(userRepository.findById(id).orElse(null));
//        }
//        else{
//            return ResponseEntity.noContent().build();
//        }
        User user=userRepository.findById(id).orElse(null);
        if(user==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateDetails(@RequestBody Book book, @PathVariable long id){
        Book buk = null;
        Optional<Book> updatebook = bookRepository.findById(id);
        if(updatebook.isPresent()){
            buk = updatebook.get();
            buk.setId(id);
            buk.setName(book.getName());
            bookRepository.save(buk);
            return ResponseEntity.ok().body(buk);
        }
        else{
            return ResponseEntity.noContent().build();
        }}

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Boolean> deleteBook(@PathVariable long id){
            Optional<Book> buk = bookRepository.findById(id);
            if(buk.isEmpty()){
                return ResponseEntity.ok(false);
            }
            else{
                bookRepository.deleteById(id);
                return ResponseEntity.ok(true);
            }
        }
    }


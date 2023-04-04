package api;

import dao.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BookService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookApi {
    public final BookService bookService;

    @Autowired
    public BookApi(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long id) {
        Optional<Book> bk = bookService.getBookById(id);
        if (bk.isPresent()) {
            return new ResponseEntity<>(bk.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Book bk) {
        boolean success = bookService.addBook(bk);
        if (success) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable(value = "id") Long id, @RequestBody Book bk) {
        boolean success = bookService.updateBook(id, bk);
        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id) {
        boolean success = bookService.deleteBook(id);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

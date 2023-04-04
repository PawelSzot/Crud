package service;

import dao.BookRepo;
import dao.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    public final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepo.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepo.findById(id);
    }

    public boolean addBook(Book book) {
        bookRepo.save(book);
        return true;
    }

    public boolean updateBook(Long id, Book book) {
        Optional<Book> bk = bookRepo.findById(id);
        if (bk.isPresent()) {
            book.setId(id);
            bookRepo.save(book);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteBook(Long id) {
        bookRepo.deleteById(id);
        return true;
    }
}

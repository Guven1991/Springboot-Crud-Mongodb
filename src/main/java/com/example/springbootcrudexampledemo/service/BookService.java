package com.example.springbootcrudexampledemo.service;

import com.example.springbootcrudexampledemo.dto.AuthorDto;
import com.example.springbootcrudexampledemo.dto.BookDto;
import com.example.springbootcrudexampledemo.entity.Book;
import com.example.springbootcrudexampledemo.error.AuthorNotFoundException;
import com.example.springbootcrudexampledemo.error.BookNotFoundException;
import com.example.springbootcrudexampledemo.error.BookUnSupportedFieldPatchException;
import com.example.springbootcrudexampledemo.repository.BookRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository,@Lazy AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public BookDto createBook(BookDto bookDto) {

        if (bookDto.getAuthor() == null) {
            throw new AuthorNotFoundException(bookDto.getId());
        }

        AuthorDto authorDto = authorService.createAuthor(bookDto.getAuthor());
        bookDto.setAuthor(authorDto);

        Book book = bookRepository.save(dozerBeanMapper.map(bookDto, Book.class));

        return dozerBeanMapper.map(book, BookDto.class);
    }

    public List<BookDto> findAllBook() {
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream().map(book ->
                dozerBeanMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    public BookDto findOneBook(String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return dozerBeanMapper.map(book, BookDto.class);

    }

    public BookDto updateBook(BookDto bookDto, String id) {
        Book book = dozerBeanMapper.map(bookDto, Book.class);
        Book updateBook = bookRepository.findById(id).map(x -> {
            x.setName(book.getName());
            x.setAuthor(book.getAuthor());
            x.setPrice(book.getPrice());
            return bookRepository.save(x);
        }).orElseGet(() -> {
            book.setId(id);
            return bookRepository.save(book);
        });

        return dozerBeanMapper.map(updateBook, BookDto.class);
    }

    public BookDto patchPrice(Map<Double, Double> update, String id) {

        Book book = bookRepository.findById(id)
                .map(x -> {
                    Double price = update.get(50.04);
                    if (!(price == null)) {
                        x.setPrice(price);
                        return bookRepository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }
                }).orElseThrow(() -> new BookNotFoundException(id));

        return dozerBeanMapper.map(book, BookDto.class);
    }

    public void deleteBook(String id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }

    public List<BookDto> getAllBooksByAuthorId(String id) {
        List<Book> bookList = bookRepository.getBooksByAuthor_Id(id);

        return bookList.stream().map(book ->
                dozerBeanMapper.map(book, BookDto.class)).collect(Collectors.toList());

    }
}

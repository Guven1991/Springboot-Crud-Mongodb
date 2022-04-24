package service;

import com.example.springbootcrudexampledemo.dto.AuthorDto;
import com.example.springbootcrudexampledemo.dto.BookDto;
import com.example.springbootcrudexampledemo.entity.Author;
import com.example.springbootcrudexampledemo.entity.Book;
import com.example.springbootcrudexampledemo.repository.AuthorRepository;
import com.example.springbootcrudexampledemo.repository.BookRepository;
import com.example.springbootcrudexampledemo.service.AuthorService;
import com.example.springbootcrudexampledemo.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookService bookService;

    @InjectMocks
    @Spy
    private AuthorService authorService;

    private BookDto bookDto;

    private Book book;

    private Author author;

    private AuthorDto authorDto;

    @Before
    public void init() {

        author = Author.builder()
                .id("1")
                .name("guven")
                .lastname("ayvaz")
                .build();



        authorDto = AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .lastname(author.getLastname())
                .build();

        book = Book.builder()
                .id("1")
                .name("ali ata bak")
                .author(author)
                .price(50.05)
                .build();

        bookDto = BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(authorDto)
                .price(book.getPrice())
                .build();
    }

    @Test
    public void createAuthor() {
        when(authorRepository.save(any())).thenReturn(author);

        AuthorDto authorDtoReturned = authorService.createAuthor(authorDto);

        assertEquals(Optional.of("1"), Optional.ofNullable(authorDtoReturned.getId()));
        assertEquals("guven", authorDtoReturned.getName());
    }

    @Test
    public void getAllAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(author));
        List<AuthorDto> authorDtoList = authorService.getAllAuthors();
        assertEquals(1, authorDtoList.size());
        assertEquals("guven", authorDtoList.get(0).getName());
    }

    @Test
    public void getAuthorById() {
        when(authorRepository.findById(any())).thenReturn(Optional.ofNullable(author));
        when(bookService.getAllBooksByAuthorId(any())).thenReturn(List.of(bookDto));
        AuthorDto authorDto = authorService.getAuthorById("1");
        assertEquals("1", authorDto.getId());
        assertEquals("guven", authorDto.getName());
    }

    @Test
    public void updateAuthor() {

        Author updatedAuthor = Author.builder()
                .id("1")
                .name("ali")
                .lastname("ayvaz")
                .build();

        AuthorDto reqAuthorDto = AuthorDto.builder()
                .id("1")
                .name("ali")
                .lastname("ayvaz")
                .build();

        when(authorRepository.findById("1")).thenReturn(Optional.of(author));
        when(authorRepository.save(any())).thenReturn(updatedAuthor);

        AuthorDto updatedAuthorDto = authorService.updateAuthor( reqAuthorDto,"1");
        assertEquals("ali", updatedAuthorDto.getName());
    }

    @Test
    public void deleteAuthor() {
//        when(authorRepository.existsById(any())).thenReturn(true);
        authorRepository.deleteById("1");
        verify(authorRepository).deleteById("1");
    }

}

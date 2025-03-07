package com.example.demo;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    Book findByTitle(String title);

    List<Book> findByAgeGreaterThanEqual(Long age, Sort sort);

    List<Book> findByPublishDateAfter(LocalDateTime publishDate, Pageable pageable);

    List<Book> findByTitleContains(String string);

    List<Book> findByAuthorAndAgeGreaterThan(String author, Long age); // order of the args and the func name should be matched

    long countByAuthor(String author);

    boolean existsByAuthor(String author);


    @Query("select b from Book b where b.title = :title")
    Book findByTitleQuery(String title);

    @Query("select b from Book b where b.age >= :age")
    List<Book> findByAgeGreaterThanEqualQuery(Long age, Sort sort);

    @Query("select b from Book b where b.publishDate > :publishDate")
    List<Book> findByPublisDateAfterQuery(LocalDateTime publishDate, PageRequest pageRequest);

    @Query("select b from Book b where b.title like %:title%")
    List<Book> findByTitleContainsQuery(String title);

    @Query("select b from Book b where b.author = :author and b.age >= :age")
    List<Book> findByAuthorAndAgeGreaterThanEqualQuery(String author, Long age);

    @Query("select count(b) from Book b where b.author = :author")
    long countByAuthorQuery(String author);

}

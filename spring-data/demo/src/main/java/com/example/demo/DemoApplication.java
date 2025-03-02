package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		BookRepository bookRepository = context.getBean(BookRepository.class);
		List<Book> exampleBooks = new ArrayList<>();
		exampleBooks.add(new Book(null, "Clean Code", "Robert C. Martin", 11L, LocalDateTime.parse("2008-07-11T00:00:00")));
		exampleBooks.add(new Book(null, "Design Patterns", "Erich Gamma", 29L, LocalDateTime.parse("1994-10-21T00:00:00")));
		exampleBooks.add(new Book(null, "Refactoring", "Martin Fowler", 22L, LocalDateTime.parse("1999-07-08T00:00:00")));
		exampleBooks.add(new Book(null, "Effective Java", "Joshua Bloch", 19L, LocalDateTime.parse("2001-05-28T00:00:00")));
		exampleBooks.add(new Book(null, "Java Concurrency in Practice", "Brian Goetz", 17L, LocalDateTime.parse("2006-05-19T00:00:00")));

		bookRepository.saveAll(exampleBooks);

		Book books = bookRepository.findByTitle(("Clean Code"));
		System.out.println(books);
		List<Book> booksByAge = bookRepository.findByAgeGreaterThanEqual(15L, Sort.by("age").ascending());
		System.out.println(booksByAge);
		List<Book> booksByPublishDate = bookRepository.findByPublishDateAfter(LocalDateTime.parse("1995-01-01T00:00:00"),
				PageRequest.of(1, 5, Sort.by("publishDate")));    //////////////////////////////////////////////does not show the wanted books
		System.out.println(booksByPublishDate);

		List<Book> booksByTitleContains = bookRepository.findByTitleContains("Java");
		System.out.println(booksByTitleContains);

		List<Book> booksByAuthorAndAge = bookRepository.findByAuthorAndAgeGreaterThan("Robert C. Martin", 10L);
		System.out.println(booksByAuthorAndAge);

		long countByAuthor = bookRepository.countByAuthor("Erich Gamma");
		System.out.println(countByAuthor);

		boolean existsByAuthor = bookRepository.existsByAuthor("Joshua Bloch");
		System.out.println(existsByAuthor);


		Book cleancode = bookRepository.findByTitleQuery("Clean Code");
		System.out.println(cleancode);

		List<Book> byAge = bookRepository.findByAgeGreaterThanEqualQuery(15L, Sort.by("age"));
		System.out.println(byAge);

		List<Book> byPublishDate = bookRepository.findByPublisDateAfterQuery(LocalDateTime.parse("1995-01-01T00:00:00"), PageRequest.of(1, 5));
		System.out.println(byPublishDate);

		List<Book> byNameContains = bookRepository.findByTitleContainsQuery("Java");
		System.out.println(byNameContains);

		List<Book> byAuthorAndAgeGreaterThanEqualQuery = bookRepository.findByAuthorAndAgeGreaterThanEqualQuery("Robert C. Martin", 10L);
		System.out.println(byAuthorAndAgeGreaterThanEqualQuery);

		long byCountAuthor = bookRepository.countByAuthorQuery("Joshua Bloch");
		System.out.println(byCountAuthor);
	}

}

package org.danikzhezmer.schoolkitchen.repository;

import org.danikzhezmer.schoolkitchen.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {

}

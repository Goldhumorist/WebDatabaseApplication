package com.WebDatabaseApplication.repository;

import com.WebDatabaseApplication.entity.Product;
import com.WebDatabaseApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByModelProduct (String tag);
    List<Product>  findByType (String tag);
    List<Product> findAllByAuthor(User author);
    List<Product> findAllByStatus(String status);
}

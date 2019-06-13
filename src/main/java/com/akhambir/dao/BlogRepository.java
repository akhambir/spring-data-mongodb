package com.akhambir.dao;

import com.akhambir.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BlogRepository extends MongoRepository<Blog, String> {

    Optional<Blog> findByBlogName(String name);

}

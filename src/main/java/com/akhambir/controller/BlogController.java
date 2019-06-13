package com.akhambir.controller;

import com.akhambir.dao.BlogRepository;
import com.akhambir.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    public ResponseEntity<Blog> create(@RequestBody Blog blog) {
        Blog result = blogRepository.insert(blog);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/blog", method = RequestMethod.PUT)
    public ResponseEntity<Blog> update(@RequestBody Blog blog) {
        return blogRepository.findByBlogName(blog.getBlogName())
                .map(b -> { blog.setId(b.getId()); return blog; })
                .map(blogRepository::save)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

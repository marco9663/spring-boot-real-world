package com.example.realworld.repository;

import com.example.realworld.model.Article;
import com.example.realworld.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}

package com.example.realworld.repository;

import com.example.realworld.model.Comment;
import com.example.realworld.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}

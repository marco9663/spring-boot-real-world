package com.example.realworld.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedAt")
    private Date updatedAt;
    @Column(name="body")
    private String body;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

//    public Comment(Date createdAt, Date updatedAt, String body, User author) {
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//        this.body = body;
//        this.author = author;
//    }
}

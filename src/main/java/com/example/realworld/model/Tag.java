package com.example.realworld.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tags")
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="name")
    private String name;
}

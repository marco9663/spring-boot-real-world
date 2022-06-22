package com.example.realworld.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Table(name="followers")
@Data
public class Follower {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name="profileId")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name="followedById")
    private Profile followedBy;

}

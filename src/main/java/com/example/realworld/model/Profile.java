package com.example.realworld.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="profiles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Profile {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name="username", nullable = false)
    private String username;
    @Column(name="bio")
    private String bio;
    @Column(name="image")
    private String image;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="follower",
            joinColumns={@JoinColumn(name="profile", referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name="followedBy", referencedColumnName = "id")})
    private Set<Profile> followers = new HashSet<Profile>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="follower",
            joinColumns={@JoinColumn(name="followedBy", referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name="profile", referencedColumnName = "id")})
    private Set<Profile> followings = new HashSet<Profile>();


//    public Profile(String username, String bio, String image, User user) {
//        this.user = user;;
//        this.username = username;
//        this.bio = bio;
//        this.image = image;
//    }
//
//    public Profile() {
//
//    }
}

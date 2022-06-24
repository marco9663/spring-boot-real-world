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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="follower",
            joinColumns={@JoinColumn(name="profile")},
            inverseJoinColumns={@JoinColumn(name="followedBy")})
    private Set<Profile> followers = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
//    @JoinTable(name="follower",
//            joinColumns={@JoinColumn(name="followedBy")},
//            inverseJoinColumns={@JoinColumn(name="profile") })
    private Set<Profile> followings = new HashSet<>();


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

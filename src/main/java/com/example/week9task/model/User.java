package com.example.week9task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    private boolean flag;

    @OneToMany
    @JsonIgnore
    private Set<UserConnections> friendList;

    @OneToMany
//    @JsonIgnore
    private List<Posts> posts = new ArrayList<>();

    @OneToMany
    private List<Comments> comments = new ArrayList<>();

    @OneToMany
    private Set<Posts> favorites = new HashSet<>();

    @OneToMany
    private List<Comments> likedComments = new ArrayList<>();

}

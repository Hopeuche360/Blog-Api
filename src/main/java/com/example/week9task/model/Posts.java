package com.example.week9task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_content", nullable = false)
    private String content;

    private int quantityOfLikes;

    @OneToMany
    private List<Comments> comments = new ArrayList<>();

    @OneToMany
    @JsonIgnore
    private Set<PostLikes> postLikes = new HashSet<>();
}

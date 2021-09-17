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
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_content", nullable = false)
    private String content;

    @Column(name = "like_quantity", nullable = false)
    private int likeQuantity;

    @OneToMany
    @JsonIgnore
    private Set<CommentLikes> commentLikes = new HashSet<>();

//    @OneToMany
//    private List<Comments> likedComments = new ArrayList<>();
}

package com.example.week9task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CommentLikes {
    @Id
    @Column(name = "like_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Comments comments;

    @OneToOne (cascade = CascadeType.REMOVE, orphanRemoval = true)
    private User user;
}

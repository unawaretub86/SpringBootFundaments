package com.fundamentos.springboot.fundamentos.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter @ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false, unique = true)
    private long id;

    @Column(name = "description", length = 255)
    private String description;
    @ManyToOne
    private User user;

    public Post() {
    }

    public Post(String description, User user) {
        this.description = description;
        this.user = user;
    }
}

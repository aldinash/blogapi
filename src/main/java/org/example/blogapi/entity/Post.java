package org.example.blogapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="native")
    @GenericGenerator(name = "native")
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;
}
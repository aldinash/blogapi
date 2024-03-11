package org.example.blogapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@Entity
@Table(
        name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String content;
}
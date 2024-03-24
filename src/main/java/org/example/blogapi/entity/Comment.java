package org.example.blogapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="native")
    @GenericGenerator(name = "native")
    private Long id;
    private String body;
    private String email;
    private String name;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="postid", nullable=false)
    private Post post;
}

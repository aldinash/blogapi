package org.example.blogapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="native")
    @GenericGenerator(name = "native")
    private Long id;
    private String name;
}

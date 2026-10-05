package com.example.LibraryManagement.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Authors")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(nullable = false)
    private String authorName;

     @OneToMany(mappedBy = "Id", cascade = CascadeType.ALL)
     private List<Book> books;

    @Column(unique = true, nullable = false)
    private String email;

}

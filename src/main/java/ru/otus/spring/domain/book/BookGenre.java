package ru.otus.spring.domain.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_genres")
public class BookGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    public BookGenre(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BookGenre{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

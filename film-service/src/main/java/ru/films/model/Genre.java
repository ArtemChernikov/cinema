package ru.films.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static ru.films.exception.message.GenreExceptionMessage.NAME_VALUE_IS_INVALID;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 06.05.2024
 */
@Entity
@Table(name = "genres")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = NAME_VALUE_IS_INVALID)
    @Column(unique = true)
    private String name;
}

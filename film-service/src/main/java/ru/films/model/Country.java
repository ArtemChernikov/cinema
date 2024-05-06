package ru.films.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import static ru.films.exception.message.CountryExceptionMessage.NAME_VALUE_IS_INVALID;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 06.05.2024
 */
@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = NAME_VALUE_IS_INVALID)
    @Column(unique = true)
    private String name;
}

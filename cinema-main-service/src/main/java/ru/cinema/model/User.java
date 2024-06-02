package ru.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

/**
 *
 * @author Artem Chernikov
 * @version 1.1
 * @since 26.02.2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String name;

    private String surname;

    private String email;

    @EqualsAndHashCode.Include
    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}

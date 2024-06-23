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
 * @version 1.0
 * @since 09.02.2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private FilmSession filmSession;

    @Column(name = "row_number")
    private Integer rowNumber;

    @Column(name = "place_number")
    private Integer placeNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

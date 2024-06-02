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
 * @since 23.02.2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String name;

    @Column(name = "row_count")
    private int rowCount;

    @Column(name = "place_count")
    private int placeCount;

    private String description;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;

}

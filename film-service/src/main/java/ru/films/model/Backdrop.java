package ru.films.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static ru.films.exception.message.BackdropExceptionMessage.PREVIEW_URL_VALUE_IS_INVALID;
import static ru.films.exception.message.BackdropExceptionMessage.URL_VALUE_IS_INVALID;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 28.04.2024
 */
@Entity
@Table(name = "backdrops")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Backdrop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = URL_VALUE_IS_INVALID)
    private String url;

    @NotBlank(message = PREVIEW_URL_VALUE_IS_INVALID)
    @Column(name = "preview_url")
    private String previewUrl;
}

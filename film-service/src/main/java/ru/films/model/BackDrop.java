package ru.films.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.films.exception.message.BackDropExceptionMessage;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import static ru.films.exception.message.BackDropExceptionMessage.PREVIEW_URL_VALUE_IS_INVALID;
import static ru.films.exception.message.BackDropExceptionMessage.URL_VALUE_IS_INVALID;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 28.04.2024
 */
@Table(name = "back_drops")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackDrop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = URL_VALUE_IS_INVALID)
    private String url;

    @NotBlank(message = PREVIEW_URL_VALUE_IS_INVALID)
    @Column(name = "preview_url")
    private String previewUrl;
}

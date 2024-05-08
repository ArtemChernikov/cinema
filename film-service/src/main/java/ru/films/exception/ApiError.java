package ru.films.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 08.05.2024
 */
@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String reason;
    private String message;
    private StackTraceElement[] errors;
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime errorTimestamp;
}

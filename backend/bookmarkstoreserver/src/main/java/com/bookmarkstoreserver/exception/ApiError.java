package com.bookmarkstoreserver.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created 11/02/2022 - 16:05
 * @Package com.bookmarkstoreserver.exception
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private List errors = new ArrayList<>();
    private String path;
}

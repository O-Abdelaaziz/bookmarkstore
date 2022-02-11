package com.bookmarkstoreserver.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Created 11/02/2022 - 16:54
 * @Package com.bookmarkstoreserver.exception.custom
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}

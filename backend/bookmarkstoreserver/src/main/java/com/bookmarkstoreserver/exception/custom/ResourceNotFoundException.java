package com.bookmarkstoreserver.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Created 11/02/2022 - 16:33
 * @Package com.bookmarkstoreserver.exception.custom
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

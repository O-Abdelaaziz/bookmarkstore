package com.bookmarkstoreserver.exception;

import org.springframework.http.ResponseEntity;

/**
 * @Created 11/02/2022 - 16:09
 * @Package com.bookmarkstoreserver.exception
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}

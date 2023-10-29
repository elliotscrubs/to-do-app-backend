package com.szaboildiko.todoapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class CategoryNotFoundException extends RuntimeException {

    CategoryNotFoundException(Integer id) {
        super("Could not find category " + id);
    }
}
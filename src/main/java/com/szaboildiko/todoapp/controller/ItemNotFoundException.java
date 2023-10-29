package com.szaboildiko.todoapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ItemNotFoundException extends RuntimeException {

    ItemNotFoundException(Integer id) {
        super("Could not find item " + id);
    }
}
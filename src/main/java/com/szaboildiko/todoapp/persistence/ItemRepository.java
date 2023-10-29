package com.szaboildiko.todoapp.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    Collection<Item> findByCategoryId(Integer categoryId);


}
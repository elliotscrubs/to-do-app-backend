package com.szaboildiko.todoapp.controller;

import com.szaboildiko.todoapp.persistence.Item;
import com.szaboildiko.todoapp.persistence.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/item")
public class ItemController {
    @Autowired

    private ItemRepository itemRepository;

    @PostMapping()
    public @ResponseBody String addNewItem (@RequestBody Item item) {
        itemRepository.save(item);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Integer> deleteItem(@PathVariable Integer id) {


        itemRepository.deleteById(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody Item updateItem(@RequestBody Item newItem, @PathVariable Integer id) {

        return itemRepository.findById(id)
                .map(item -> {
                    item.setTask(newItem.getTask());
                    return itemRepository.save(item);
                })
                .orElseThrow(() -> new ItemNotFoundException(id));

    }

    @GetMapping(value = "/{id}")
    public @ResponseBody Item getItem(@PathVariable Integer id) {

        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    static
    class ItemNotFoundException extends RuntimeException {

        ItemNotFoundException(Integer id) {
            super("Could not find item " + id);
        }
    }
}


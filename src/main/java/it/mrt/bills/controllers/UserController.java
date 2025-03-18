package it.mrt.bills.controllers;

import it.mrt.bills.dtos.UserDTO;
import it.mrt.bills.dtos.filters.UserFilters;
import it.mrt.bills.entities.User;
import it.mrt.bills.mappers.UserMapper;
import it.mrt.bills.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @PostMapping("save")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO dto) {
        User user = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(user));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable UUID id) {
        User user = userService.findById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(user));
    }

    @GetMapping
    public ResponseEntity<Collection<UserDTO>> getAll() {
        List<UserDTO> users = userService.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable UUID id) {
        User user = userService.deleteById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(user));
    }

    @PostMapping("filter")
    public ResponseEntity<Collection<UserDTO>> filter(@RequestBody UserFilters userFilters) {
        Collection<UserDTO> users = userService.filter(userFilters).stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(users);
    }
}

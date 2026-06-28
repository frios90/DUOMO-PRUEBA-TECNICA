package com.doumo.app.controller;

import com.doumo.app.dto.PageListResponse;
import com.doumo.app.dto.PersonRequest;
import com.doumo.app.dto.PersonResponse;
import com.doumo.app.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(@Valid @RequestBody PersonRequest request) {
        PersonResponse response = personService.createPerson(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> listPeople() {
        List<PersonResponse> people = personService.listPeople();
        return ResponseEntity.ok(people);
    }

    @GetMapping("/paginate")
    public ResponseEntity<PageListResponse<PersonResponse>> pageListPeople(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        PageListResponse<PersonResponse> pageResponse = personService.pageListPeople(page, size);
        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable String id) {
        PersonResponse response = personService.getPersonById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable String id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
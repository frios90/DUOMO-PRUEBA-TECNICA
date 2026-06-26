package com.doumo.app.repository;

import com.doumo.app.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PersonRepository {
    private final Map<String, Person> persons = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Person save(Person person) {
        if (person.getId() == null || person.getId().isEmpty()) {
            person.setId(String.valueOf(idGenerator.getAndIncrement()));
        }
        persons.put(person.getId(), person);
        return person;
    }

    public List<Person> findAll() {
        return new ArrayList<>(persons.values());
    }

    public Optional<Person> findById(String id) {
        return Optional.ofNullable(persons.get(id));
    }

    public void deleteById(String id) {
        persons.remove(id);
    }

    public boolean existsById(String id) {
        return persons.containsKey(id);
    }
}
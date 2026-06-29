package com.doumo.app.repository;

import com.doumo.app.model.Person;
import com.doumo.app.util.PersonDataUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {
    private final Map<String, Person> persons = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(21);

    @PostConstruct
    public void init() {
        if (persons.isEmpty()) {
            List<Person> sampleUsers = PersonDataUtil.getSampleUsers();
            for (Person person : sampleUsers) {
                persons.put(person.getId(), person);
            }
        }
    }

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

    public List<Person> findPaginated(int page, int size, String search) {
        return persons.values().stream()
            .filter(
                p -> search == null || search.isEmpty() ||
                p.getName().toLowerCase().contains(search.toLowerCase()) ||
                p.getLastName().toLowerCase().contains(search.toLowerCase()) ||
                p.getEmail().toLowerCase().contains(search.toLowerCase())
            )
            .sorted((p1, p2) -> Integer.parseInt(p2.getId()) - Integer.parseInt(p1.getId()))
            .skip((long) page * size)
            .limit(size)
            .collect(Collectors.toList());
    }

    public int getTotalCount(String search) {
        if (search == null || search.isEmpty()) {
            return persons.size();
        }
        return (int) persons.values().stream()
            .filter(
                p -> p.getName().toLowerCase().contains(search.toLowerCase()) ||
                p.getLastName().toLowerCase().contains(search.toLowerCase()) ||
                p.getEmail().toLowerCase().contains(search.toLowerCase())
            )
            .count();
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
package com.doumo.app.service;

import com.doumo.app.dto.PersonRequest;
import com.doumo.app.dto.PersonResponse;
import com.doumo.app.model.Person;
import com.doumo.app.repository.PersonRepository;
import com.doumo.app.util.RegionDataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public PersonResponse createPerson(PersonRequest request) {
        // Validate that region and commune exist and match
        validateRegionAndCommune(request.getRegionId(), request.getCommuneId());

        Person person = new Person();
        person.setName(request.getName());
        person.setLastName(request.getLastName());
        person.setEmail(request.getEmail());
        person.setAge(request.getAge());
        person.setRegionId(request.getRegionId());
        person.setCommuneId(request.getCommuneId());

        Person saved = personRepository.save(person);
        return convertToResponse(saved);
    }

    @Override
    public List<PersonResponse> listPeople() {
        return personRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PersonResponse getPersonById(String id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
        return convertToResponse(person);
    }

    @Override
    public void deletePerson(String id) {
        if (!personRepository.existsById(id)) {
            throw new RuntimeException("Person not found with id: " + id);
        }
        personRepository.deleteById(id);
    }

    private void validateRegionAndCommune(String regionId, String communeId) {
        if (!RegionDataUtil.validateRegionAndCommune(regionId, communeId)) {
            throw new RuntimeException("The commune does not belong to the selected region");
        }
    }

    private PersonResponse convertToResponse(Person person) {
        String regionName = RegionDataUtil.getRegionName(person.getRegionId());
        String communeName = RegionDataUtil.getCommuneName(person.getRegionId(), person.getCommuneId());

        return new PersonResponse(
                person.getId(),
                person.getName(),
                person.getLastName(),
                person.getEmail(),
                person.getAge(),
                regionName,
                communeName
        );
    }
}
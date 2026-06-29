package com.doumo.app.service;

import com.doumo.app.dto.PageListResponse;
import com.doumo.app.dto.PersonRequest;
import com.doumo.app.dto.PersonResponse;

import java.util.List;

public interface PersonService {
    PersonResponse createPerson(PersonRequest request);
    List<PersonResponse> listPeople();
    PersonResponse getPersonById(String id);
    void deletePerson(String id);
    PageListResponse<PersonResponse> pageListPeople(int page, int size, String search);
}
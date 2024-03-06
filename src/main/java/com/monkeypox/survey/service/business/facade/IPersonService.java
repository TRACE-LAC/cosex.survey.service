package com.monkeypox.survey.service.business.facade;

import java.util.List;

import com.monkeypox.survey.service.model.person.Person;

public interface IPersonService {

    List<Person> findAllPersons();

    Person findById(Long id);

    Person create(Person question);

    void update(Person question);

    void delete(Long id);
}
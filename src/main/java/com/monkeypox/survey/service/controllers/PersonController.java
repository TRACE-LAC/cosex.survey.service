package com.monkeypox.survey.service.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.monkeypox.survey.service.business.facade.IPersonService;
import com.monkeypox.survey.service.model.person.Person;
import com.monkeypox.survey.service.util.JsonUtility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    /**
     * This method gets the document types
     * 
     * @return document types
     */
    @GetMapping(produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Person>> findAll() {
        List<Person> persons = new ArrayList<Person>();
        log.info("Person Controller - findAll");
        persons = personService.findAllPersons();
        log.info("Person Controller - findAll, response: " + JsonUtility.objectToJson(persons));
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    /**
     * This method gets a specific document type by id
     * 
     * @param id: document type id
     * @return document type
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Person> findById(@PathVariable("id") Long id) {
        Person person = null;
        log.info("Person Controller - findById, request: " + JsonUtility.objectToJson(id));
        person = personService.findById(id);
        log.info("Person Controller - findById, response: " + JsonUtility.objectToJson(person));
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    /**
     * This method creates a new document type
     * 
     * @param person
     * @return document type
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person newPerson = null;
        log.info("Person Controller - create, request: " + JsonUtility.objectToJson(person));
        newPerson = personService.create(person);
        log.info("Person Controller - create, response: " + JsonUtility.objectToJson(newPerson));
        return new ResponseEntity<>(newPerson, HttpStatus.OK);
    }

    /**
     * This method updates a document type
     * 
     * @param person
     */
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Person person) {
        log.info("Person Controller - update, request: " + JsonUtility.objectToJson(person));
        personService.update(person);
        log.info("Person Controller - update, response: updated document type");
    }

    /**
     * This method deletes a document type by id
     * 
     * @param id: document type id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        log.info("Person Controller - delete, request: {id:" + id + "}");
        personService.delete(id);
        log.info("Person Controller - delete, response: deleted document type");
    }

}

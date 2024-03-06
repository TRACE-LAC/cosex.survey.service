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

import com.monkeypox.survey.service.business.facade.IPersonAnswerService;
import com.monkeypox.survey.service.model.person_answer.PersonAnswer;
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
@RequestMapping("person-answer")
public class PersonAnswerController {

    @Autowired
    private IPersonAnswerService personAnswerService;

    /**
     * This method gets the person answers
     * 
     * @return person answers
     */
    @GetMapping(produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PersonAnswer>> findAll() {
        List<PersonAnswer> personAnswers = new ArrayList<PersonAnswer>();
        log.info("PersonAnswer Controller - findAll");
        personAnswers = personAnswerService.findAllPersonAnswers();
        log.info("PersonAnswer Controller - findAll, response: " + JsonUtility.objectToJson(personAnswers));
        return new ResponseEntity<>(personAnswers, HttpStatus.OK);
    }

    /**
     * This method gets a specific person answer by id
     * 
     * @param id: person answer id
     * @return person answer
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonAnswer> findById(@PathVariable("id") Long id) {
        PersonAnswer personAnswer = null;
        log.info("PersonAnswer Controller - findById, request: " + JsonUtility.objectToJson(id));
        personAnswer = personAnswerService.findById(id);
        log.info("PersonAnswer Controller - findById, response: " + JsonUtility.objectToJson(personAnswer));
        return new ResponseEntity<>(personAnswer, HttpStatus.OK);
    }

    /**
     * This method creates a new person answer
     * 
     * @param personAnswer
     * @return person answer
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonAnswer> create(@RequestBody PersonAnswer personAnswer) {
        PersonAnswer newPersonAnswer = null;
        log.info("PersonAnswer Controller - create, request: " + JsonUtility.objectToJson(personAnswer));
        newPersonAnswer = personAnswerService.create(personAnswer);
        log.info("PersonAnswer Controller - create, response: " + JsonUtility.objectToJson(newPersonAnswer));
        return new ResponseEntity<>(newPersonAnswer, HttpStatus.OK);
    }

    /**
     * This method creates a personAnswers list
     * 
     * @param personAnswers
     * @return personAnswers
     */
    @PostMapping(value = "/list", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public List<PersonAnswer> createList(@RequestBody List<PersonAnswer> personAnswers) {
        List<PersonAnswer> newPersonAnswers = null;
        log.info("PersonAnswer Controller - createList, request: " + JsonUtility.objectToJson(personAnswers));
        newPersonAnswers = personAnswerService.createList(personAnswers);
        log.info("PersonAnswer Controller - createList, response: " + JsonUtility.objectToJson(newPersonAnswers));
        return newPersonAnswers;
    }

    /**
     * This method updates a person answer
     * 
     * @param personAnswer
     */
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody PersonAnswer personAnswer) {
        log.info("PersonAnswer Controller - update, request: " + JsonUtility.objectToJson(personAnswer));
        personAnswerService.update(personAnswer);
        log.info("PersonAnswer Controller - update, response: updated person answer");
    }

    /**
     * This method deletes a person answer by id
     * 
     * @param id: person answer id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        log.info("PersonAnswer Controller - delete, request: {id:" + id + "}");
        personAnswerService.delete(id);
        log.info("PersonAnswer Controller - delete, response: deleted person answer");
    }

}

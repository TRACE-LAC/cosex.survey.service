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

import com.monkeypox.survey.service.business.facade.ISurveyService;
import com.monkeypox.survey.service.model.survey.Survey;
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
@RequestMapping("surveys")
public class SurveyController {

    @Autowired
    private ISurveyService surveyService;

    /**
     * This method gets the surveys
     * 
     * @return surveys
     */
    @GetMapping(produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Survey>> findAll() {
        List<Survey> surveys = new ArrayList<Survey>();
        log.info("Survey Controller - findAll");
        surveys = surveyService.findAllSurveys();
        log.info("Survey Controller - findAll, response: " + JsonUtility.objectToJson(surveys));
        return new ResponseEntity<>(surveys, HttpStatus.OK);
    }

    /**
     * This method gets a specific survey by id
     * 
     * @param id: survey id
     * @return survey
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Survey> findById(@PathVariable("id") Long id) {
        Survey survey = null;
        log.info("Survey Controller - findById, request: " + JsonUtility.objectToJson(id));
        survey = surveyService.findById(id);
        log.info("Survey Controller - findById, response: " + JsonUtility.objectToJson(survey));
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }

    /**
     * This method creates a new survey
     * 
     * @param survey
     * @return survey
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Survey> create(@RequestBody Survey survey) {
        Survey newSurvey = null;
        log.info("Survey Controller - create, request: " + JsonUtility.objectToJson(survey));
        newSurvey = surveyService.create(survey);
        log.info("Survey Controller - create, response: " + JsonUtility.objectToJson(newSurvey));
        return new ResponseEntity<>(newSurvey, HttpStatus.OK);
    }

    /**
     * This method creates a new list of surveys
     * 
     * @param surveys
     * @return surveys
     */
    @PostMapping(value = "/surveys", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody List<Survey> surveys) {
        List<Survey> newSurveys = new ArrayList<Survey>();
        log.info("Survey Controller - create, request: " + JsonUtility.objectToJson(surveys));
        surveyService.createSurveys(surveys);
        log.info("Survey Controller - create, response: " + JsonUtility.objectToJson(newSurveys));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * This method updates a survey
     * 
     * @param survey
     */
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Survey survey) {
        log.info("Survey Controller - update, request: " + JsonUtility.objectToJson(survey));
        surveyService.update(survey);
        log.info("Survey Controller - update, response: updated survey");
    }

    /**
     * This method deletes a survey by id
     * 
     * @param id: survey id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        log.info("Survey Controller - delete, request: {id:" + id + "}");
        surveyService.delete(id);
        log.info("Survey Controller - delete, response: deleted survey");
    }
}

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

import com.monkeypox.survey.service.business.facade.ISurveyAnswerService;
import com.monkeypox.survey.service.model.answer.SurveyAnswer;
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
@RequestMapping("survey")
public class SurveyAnswerController {

    @Autowired
    private ISurveyAnswerService surveyAnswerService;

    /**
     * This method gets the survey answers
     * 
     * @return survey answers
     */
    @GetMapping(produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<SurveyAnswer>> findAll() {
        List<SurveyAnswer> answers = new ArrayList<SurveyAnswer>();
        log.info("SurveyAnswer Controller - findAll");
        answers = surveyAnswerService.findAllSurveyAnswers();
        log.info("SurveyAnswer Controller - findAll, response: " + JsonUtility.objectToJson(answers));
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    /**
     * This method gets a specific survey answer by id
     * 
     * @param id: survey answer id
     * @return survey answer
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SurveyAnswer> findById(@PathVariable("id") Long id) {
        SurveyAnswer answer = null;
        log.info("SurveyAnswer Controller - findById, request: " + JsonUtility.objectToJson(id));
        answer = surveyAnswerService.findById(id);
        log.info("SurveyAnswer Controller - findById, response: " + JsonUtility.objectToJson(answer));
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * This method creates a new survey answer
     * 
     * @param answer
     * @return survey answer
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SurveyAnswer> create(@RequestBody SurveyAnswer answer) {
        SurveyAnswer newAnswer = null;
        log.info("SurveyAnswer Controller - create, request: " + JsonUtility.objectToJson(answer));
        newAnswer = surveyAnswerService.create(answer);
        log.info("SurveyAnswer Controller - create, response: " + JsonUtility.objectToJson(newAnswer));
        return new ResponseEntity<>(newAnswer, HttpStatus.OK);
    }

    /**
     * This method creates a new list of survey answers
     * 
     * @param answers
     * @return survey answer
     */
    @PostMapping(value = "/answers", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody List<SurveyAnswer> answers) {
        List<SurveyAnswer> newAnswers = new ArrayList<SurveyAnswer>();
        log.info("SurveyAnswer Controller - create, request: " + JsonUtility.objectToJson(answers));
        surveyAnswerService.createSurveyAnswers(answers);
        log.info("SurveyAnswer Controller - create, response: " + JsonUtility.objectToJson(newAnswers));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * This method updates a survey answer
     * 
     * @param answer
     */
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody SurveyAnswer answer) {
        log.info("SurveyAnswer Controller - update, request: " + JsonUtility.objectToJson(answer));
        surveyAnswerService.update(answer);
        log.info("SurveyAnswer Controller - update, response: updated survey answer");
    }

    /**
     * This method deletes a survey answer by id
     * 
     * @param id: survey answer id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        log.info("SurveyAnswer Controller - delete, request: {id:" + id + "}");
        surveyAnswerService.delete(id);
        log.info("SurveyAnswer Controller - delete, response: deleted survey answer");
    }
}

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

import com.monkeypox.survey.service.business.facade.IQuestionService;
import com.monkeypox.survey.service.model.question.Question;
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
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    /**
     * This method gets the questions
     * 
     * @return questions
     */
    @GetMapping(produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Question>> findAll() {
        List<Question> questions = new ArrayList<Question>();
        log.info("Question Controller - findAll");
        questions = questionService.findAllQuestions();
        log.info("Question Controller - findAll, response: " + JsonUtility.objectToJson(questions));
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /**
     * This method gets the questions with answers
     * 
     * @return questions with answers
     */
    @GetMapping(value = "/answers", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Question>> findAllWithAnswers() {
        List<Question> questions = new ArrayList<Question>();
        log.info("Question Controller - findAllWithAnswers");
        questions = questionService.findAllQuestionsWithAnswers();
        log.info("Question Controller - findAllWithAnswers, response: " + JsonUtility.objectToJson(questions));
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /**
     * This method gets the questions with answers by section
     * 
     * @return questions with answers
     */
    @GetMapping(value = "/answers/{section}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Question>> findWithAnswersBySection(@PathVariable("section") String section) {
        List<Question> questions = new ArrayList<Question>();
        log.info("Question Controller - findWithAnswersBySection");
        questions = questionService.findQuestionsWithAnswersBySection(section);
        log.info("Question Controller - findWithAnswersBySection, response: " + JsonUtility.objectToJson(questions));
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /**
     * This method gets a specific question by id
     * 
     * @param id: question id
     * @return question
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Question> findById(@PathVariable("id") Long id) {
        Question question = null;
        log.info("Question Controller - findById, request: " + JsonUtility.objectToJson(id));
        question = questionService.findById(id);
        log.info("Question Controller - findById, response: " + JsonUtility.objectToJson(question));
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    /**
     * This method creates a new question
     * 
     * @param question
     * @return question
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Question> create(@RequestBody Question question) {
        Question newQuestion = null;
        log.info("Question Controller - create, request: " + JsonUtility.objectToJson(question));
        newQuestion = questionService.create(question);
        log.info("Question Controller - create, response: " + JsonUtility.objectToJson(newQuestion));
        return new ResponseEntity<>(newQuestion, HttpStatus.OK);
    }

    /**
     * This method updates a question
     * 
     * @param question
     */
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Question question) {
        log.info("Question Controller - update, request: " + JsonUtility.objectToJson(question));
        questionService.update(question);
        log.info("Question Controller - update, response: updated question");
    }

    /**
     * This method deletes a question by id
     * 
     * @param id: question id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        log.info("Question Controller - delete, request: {id:" + id + "}");
        questionService.delete(id);
        log.info("Question Controller - delete, response: deleted question");
    }

}
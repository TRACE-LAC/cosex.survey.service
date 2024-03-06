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

import com.monkeypox.survey.service.business.facade.IAnswerService;
import com.monkeypox.survey.service.model.answer.Answer;
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
@RequestMapping("answer")
public class AnswerController {

    @Autowired
    private IAnswerService answerService;

    /**
     * This method gets the answers
     * 
     * @return answers
     */
    @GetMapping(produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Answer>> findAll() {
        List<Answer> answers = new ArrayList<Answer>();
        log.info("Answer Controller - findAll");
        answers = answerService.findAllAnswers();
        log.info("Answer Controller - findAll, response: " + JsonUtility.objectToJson(answers));
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    /**
     * This method gets a specific answer by id
     * 
     * @param id: answer id
     * @return answer
     */
    @GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Answer> findById(@PathVariable("id") Long id) {
        Answer answer = null;
        log.info("Answer Controller - findById, request: " + JsonUtility.objectToJson(id));
        answer = answerService.findById(id);
        log.info("Answer Controller - findById, response: " + JsonUtility.objectToJson(answer));
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    /**
     * This method creates a new answer
     * 
     * @param answer
     * @return answer
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Answer> create(@RequestBody Answer answer) {
        Answer newAnswer = null;
        log.info("Answer Controller - create, request: " + JsonUtility.objectToJson(answer));
        newAnswer = answerService.create(answer);
        log.info("Answer Controller - create, response: " + JsonUtility.objectToJson(newAnswer));
        return new ResponseEntity<>(newAnswer, HttpStatus.OK);
    }

    /**
     * This method updates a answer
     * 
     * @param answer
     */
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Answer answer) {
        log.info("Answer Controller - update, request: " + JsonUtility.objectToJson(answer));
        answerService.update(answer);
        log.info("Answer Controller - update, response: updated answer");
    }

    /**
     * This method deletes a answer by id
     * 
     * @param id: answer id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        log.info("Answer Controller - delete, request: {id:" + id + "}");
        answerService.delete(id);
        log.info("Answer Controller - delete, response: deleted answer");
    }

}

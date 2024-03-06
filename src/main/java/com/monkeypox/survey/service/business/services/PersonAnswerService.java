package com.monkeypox.survey.service.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Preconditions;
import com.monkeypox.survey.service.business.facade.IPersonAnswerService;
import com.monkeypox.survey.service.model.person_answer.PersonAnswer;
import com.monkeypox.survey.service.model.person_answer.PersonAnswerRepository;
import com.monkeypox.survey.service.util.JsonUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonAnswerService implements IPersonAnswerService{
    
    @Autowired
    private PersonAnswerRepository personAnswerRepository;

    /**
      * This method gets the personAnswerAnswers
      * @return personAnswerAnswers
      */
    @Override
    public List<PersonAnswer> findAllPersonAnswers() {
        List<PersonAnswer> personAnswerAnswers = new ArrayList<PersonAnswer>();
        try {
            personAnswerAnswers = personAnswerRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error querying all personAnswer answers", e);
        }
        return personAnswerAnswers;
    }

    /**
     * This method gets a specific personAnswer by id
     * @param id: personAnswer id
     * @return personAnswer
     */
    @Override
    public PersonAnswer findById(Long id) {
        PersonAnswer personAnswer = null;
        try {
            personAnswer = personAnswerRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error to query personAnswer with id: " + id, e);
        }
        return personAnswer;
    }

    /**
     * This method creates a new personAnswer
     * @param personAnswer
     * @return personAnswer
     */
    @Override
    public PersonAnswer create(PersonAnswer personAnswer) {
        PersonAnswer newPersonAnswer = null;
        try {
            Preconditions.checkNotNull(personAnswer);
            newPersonAnswer = personAnswerRepository.save(personAnswer);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error personAnswer creating: " + JsonUtility.objectToJson(personAnswer), e);
        }
        return newPersonAnswer;
    }

    /**
     * This method creates a personAnswers list
     * @param personAnswers
     * @return personAnswers
     */
    @Override
    public List<PersonAnswer> createList(List<PersonAnswer> personAnswers) {
        List<PersonAnswer> newPersonAnswers = null;
        try {
            Preconditions.checkNotNull(personAnswers);
            newPersonAnswers = personAnswerRepository.saveAll(personAnswers);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error personAnswers list creating: " + JsonUtility.objectToJson(personAnswers), e);
        }
        return newPersonAnswers;
    }
    
    /**
     * This method updates a personAnswer
     * @param personAnswer
     */
    @Override
    public void update(PersonAnswer personAnswer) {
        try {
            Preconditions.checkNotNull(personAnswer);

            PersonAnswer pUpdate = personAnswerRepository.findById(personAnswer.getId()).orElse(null);

            Preconditions.checkNotNull(pUpdate);
           
            pUpdate.setAnswerId(personAnswer.getAnswerId());
            pUpdate.setPersonId(personAnswer.getPersonId());
            pUpdate.setQuestionId(personAnswer.getQuestionId());

            personAnswerRepository.save(pUpdate);
            
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error personAnswer update: " + JsonUtility.objectToJson(personAnswer), e);
        }
    }
    
    /**
     * This method deletes a personAnswer by id
     * @param id: personAnswer id
     */
    @Override
    public void delete(Long id) {
        try {
            personAnswerRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error personAnswer deletion with id: " + id, e);
        } 
    }
}

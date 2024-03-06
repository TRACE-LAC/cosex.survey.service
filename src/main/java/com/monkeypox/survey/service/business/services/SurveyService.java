package com.monkeypox.survey.service.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monkeypox.survey.service.business.facade.ISurveyService;
import com.monkeypox.survey.service.model.survey.Survey;
import com.monkeypox.survey.service.model.survey.SurveyRepository;
import com.monkeypox.survey.service.util.JsonUtility;
import com.google.api.client.util.Preconditions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SurveyService implements ISurveyService {
    
    @Autowired
    private SurveyRepository surveyRepository;

    /**
      * This method gets the surveys
      * @return surveys
      */
    @Override
    public List<Survey> findAllSurveys() {
        List<Survey> surveys = new ArrayList<Survey>();
        try {
            surveys = surveyRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error querying all surveys", e);
        }
        return surveys;
    }

    /**
     * This method gets a specific survey by id
     * @param id: survey id
     * @return survey
     */
    @Override
    public Survey findById(Long id) {
        Survey survey = null;
        try {
            survey = surveyRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error to query survey with id: " + id, e);
        }
        return survey;
    }

    /**
     * This method creates a new survey
     * @param survey
     * @return survey
     */
    @Override
    public Survey create(Survey survey) {
        Survey newSurvey = null;
        try {
            Preconditions.checkNotNull(survey);
            newSurvey = surveyRepository.save(survey);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error survey creating: " + JsonUtility.objectToJson(survey), e);
        }
        return newSurvey;
    }

    /**
     * This method creates a new list of survey surveys
     * @param surveys
     * @return surveys
     */
    @Override
    public List<Survey> createSurveys(List<Survey> surveys) {
        List<Survey> newSurvey = new ArrayList<Survey>();
        try {
            for (Survey survey : surveys) {
                Preconditions.checkNotNull(survey);
                // survey.setDate(new DateTime(System.currentTimeMillis()));
                newSurvey.add(surveyRepository.save(survey));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error survey creating: " + JsonUtility.objectToJson(surveys), e);
        }
        return newSurvey;
    }
    
    /**
     * This method updates a survey
     * @param survey
     */
    @Override
    public void update(Survey survey) {
        try {
            Preconditions.checkNotNull(survey);

            Survey aUpdate = surveyRepository.findById(survey.getId()).orElse(null);

            Preconditions.checkNotNull(aUpdate);
           
            // aUpdate.setText(survey.getText());
            
            surveyRepository.save(aUpdate);
            
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error survey update: " + JsonUtility.objectToJson(survey), e);
        }
    }
    
    /**
     * This method deletes a survey by id
     * @param id: survey id
     */
    @Override
    public void delete(Long id) {
        try {
            surveyRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error survey deletion with id: " + id, e);
        } 
    }

}

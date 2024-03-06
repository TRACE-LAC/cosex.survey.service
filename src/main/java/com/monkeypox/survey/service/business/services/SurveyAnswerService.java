package com.monkeypox.survey.service.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monkeypox.survey.service.business.facade.ISurveyAnswerService;
import com.monkeypox.survey.service.model.answer.SurveyAnswer;
import com.monkeypox.survey.service.model.answer.SurveyAnswerRepository;
import com.monkeypox.survey.service.model.survey.Survey;
import com.monkeypox.survey.service.model.survey.SurveyRepository;
import com.monkeypox.survey.service.util.JsonUtility;
import com.google.api.client.util.Preconditions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SurveyAnswerService implements ISurveyAnswerService {
    
    @Autowired
    private SurveyAnswerRepository surveyAnswerRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    /**
      * This method gets the answers
      * @return answers
      */
    @Override
    public List<SurveyAnswer> findAllSurveyAnswers() {
        List<SurveyAnswer> answers = new ArrayList<SurveyAnswer>();
        try {
            answers = surveyAnswerRepository.findAll();
            // Long surveyId = 0L;
            // for (SurveyAnswer surveyAnswer : answers) {
            //     if(surveyAnswer.getId() > 1219) {
            //         if(surveyAnswer.getQuestion_id() == 4) {
            //             Survey survey = surveyRepository.save(new Survey());
            //             if(survey != null) {
            //                 surveyId = survey.getId();
            //                 surveyAnswer.setSurvey_id(surveyId);
            //                 surveyAnswerRepository.save(surveyAnswer);
            //             }
            //             else {
            //                 surveyId = 0L;
            //             }
            //         }
            //         else {
            //             if(surveyId > 0) {
            //               surveyAnswer.setSurvey_id(surveyId);
            //               surveyAnswerRepository.save(surveyAnswer);
            //             } 
            //         }
            //     }
            // }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error querying all answers", e);
        }
        return answers;
    }

    /**
     * This method gets a specific answer by id
     * @param id: answer id
     * @return answer
     */
    @Override
    public SurveyAnswer findById(Long id) {
        SurveyAnswer answer = null;
        try {
            answer = surveyAnswerRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error to query answer with id: " + id, e);
        }
        return answer;
    }

    /**
     * This method creates a new answer
     * @param answer
     * @return answer
     */
    @Override
    public SurveyAnswer create(SurveyAnswer answer) {
        SurveyAnswer newSurveyAnswer = null;
        try {
            Preconditions.checkNotNull(answer);
            newSurveyAnswer = surveyAnswerRepository.save(answer);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error answer creating: " + JsonUtility.objectToJson(answer), e);
        }
        return newSurveyAnswer;
    }

    /**
     * This method creates a new list of survey answers
     * @param answers-+
     * @return answers
     */
    @Override
    public List<SurveyAnswer> createSurveyAnswers(List<SurveyAnswer> answers) {
        List<SurveyAnswer> newSurveyAnswers = new ArrayList<SurveyAnswer>();
        try {
            Long surveyId = 0L;
            for (SurveyAnswer surveyAnswer : answers) {
                Preconditions.checkNotNull(surveyAnswer);
                // surveyAnswer.setDate(new DateTime(System.currentTimeMillis()));
                    if(surveyAnswer.getQuestion_id() == 4) {
                        Survey survey = surveyRepository.save(new Survey());
                        if(survey != null) {
                            surveyId = survey.getId();
                            surveyAnswer.setSurvey_id(surveyId);
                        }
                        else {
                            surveyId = 0L;
                        }
                    }
                    else {
                        if(surveyId > 0) {
                          surveyAnswer.setSurvey_id(surveyId);
                        } 
                    }
                newSurveyAnswers.add(surveyAnswerRepository.save(surveyAnswer));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error answer creating: " + JsonUtility.objectToJson(answers), e);
        }
        return newSurveyAnswers;
    }
    
    /**
     * This method updates a answer
     * @param answer
     */
    @Override
    public void update(SurveyAnswer answer) {
        try {
            Preconditions.checkNotNull(answer);

            SurveyAnswer aUpdate = surveyAnswerRepository.findById(answer.getId()).orElse(null);

            Preconditions.checkNotNull(aUpdate);
           
            // aUpdate.setText(answer.getText());
            
            surveyAnswerRepository.save(aUpdate);
            
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error answer update: " + JsonUtility.objectToJson(answer), e);
        }
    }
    
    /**
     * This method deletes a answer by id
     * @param id: answer id
     */
    @Override
    public void delete(Long id) {
        try {
            surveyAnswerRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error answer deletion with id: " + id, e);
        } 
    }

}

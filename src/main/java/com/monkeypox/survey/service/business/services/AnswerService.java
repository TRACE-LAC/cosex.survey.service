package com.monkeypox.survey.service.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monkeypox.survey.service.business.facade.IAnswerService;
import com.monkeypox.survey.service.model.answer.Answer;
import com.monkeypox.survey.service.model.answer.AnswerRepository;
import com.monkeypox.survey.service.model.question.Question;
import com.monkeypox.survey.service.util.JsonUtility;
import com.google.api.client.util.Preconditions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AnswerService implements IAnswerService {
    
    @Autowired
    private AnswerRepository answerRepository;

    /**
      * This method gets the answers
      * @return answers
      */
    @Override
    public List<Answer> findAllAnswers() {
        List<Answer> answers = new ArrayList<Answer>();
        try {
            answers = answerRepository.findAll();
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
    public Answer findById(Long id) {
        Answer answer = null;
        try {
            answer = answerRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error to query answer with id: " + id, e);
        }
        return answer;
    }

    /**
     * This method gets a specific answer by the question
     * @param question: question 
     * @return answers
     */
    @Override
    public List<Answer> findByQuestion(Question question) {
        List<Answer> answers = new ArrayList<Answer>();
        try {
            answers = answerRepository.findByQuestion(question);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error to query answers of question with id: " + question.getId(), e);
        }
        return answers;
    }

    /**
     * This method creates a new answer
     * @param answer
     * @return answer
     */
    @Override
    public Answer create(Answer answer) {
        Answer newAnswer = null;
        try {
            Preconditions.checkNotNull(answer);
            newAnswer = answerRepository.save(answer);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error answer creating: " + JsonUtility.objectToJson(answer), e);
        }
        return newAnswer;
    }
    
    /**
     * This method updates a answer
     * @param answer
     */
    @Override
    public void update(Answer answer) {
        try {
            Preconditions.checkNotNull(answer);

            Answer aUpdate = answerRepository.findById(answer.getId()).orElse(null);

            Preconditions.checkNotNull(aUpdate);
           
            aUpdate.setText(answer.getText());
            
            answerRepository.save(aUpdate);
            
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
            answerRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error answer deletion with id: " + id, e);
        } 
    }

}

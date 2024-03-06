package com.monkeypox.survey.service.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Preconditions;
import com.monkeypox.survey.service.business.facade.IQuestionService;
import com.monkeypox.survey.service.model.answer.Answer;
import com.monkeypox.survey.service.model.question.Question;
import com.monkeypox.survey.service.model.question.QuestionRepository;
import com.monkeypox.survey.service.util.JsonUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuestionService implements IQuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerService answerService;

    /**
      * This method gets all the questions
      * @return questions
      */
    @Override
    public List<Question> findAllQuestions() {
        List<Question> questions = new ArrayList<Question>();
        try {
            questions = questionRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error querying all questions", e);
        }
        return questions;
    }

    /**
      * This method gets all the questions with their answers
      * @return questions
      */
    @Override
    public List<Question> findAllQuestionsWithAnswers() {
        List<Question> questions = new ArrayList<Question>();
        List<Question> questionsWithAnswers = new ArrayList<Question>();
        List<Answer> answers = new ArrayList<Answer>();
        try {
            questions = questionRepository.findAll();
            if(questions != null && questions.size() > 0) {
                for (Question question : questions) {
                     answers = answerService.findByQuestion(question);
                     question.setAnswersInJson(JsonUtility.objectToJson(answers));
                     questionsWithAnswers.add(question);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error querying all questions with their answers", e);
        }
        return questionsWithAnswers;
    }


    /**
      * This method gets all the questions with their answers
      * @return questions
      */
    @Override
    public List<Question> findQuestionsWithAnswersBySection(String section) {
        List<Question> questions = new ArrayList<Question>();
        List<Question> questionsWithAnswers = new ArrayList<Question>();
        List<Answer> answers = new ArrayList<Answer>();
        try {
            questions = questionRepository.findBySection(section);
            if(questions != null && questions.size() > 0) {
                for (Question question : questions) {
                     answers = answerService.findByQuestion(question);
                     question.setAnswersInJson(JsonUtility.objectToJson(answers));
                     questionsWithAnswers.add(question);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error querying questions with their answers of section " + section, e);
        }
        return questionsWithAnswers;
    }

    /**
     * This method gets a specific question by id
     * @param id: question id
     * @return question
     */
    @Override
    public Question findById(Long id) {
        Question question = null;
        try {
            question = questionRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error to query question with id: " + id, e);
        }
        return question;
    }

    /**
     * This method creates a new question
     * @param question
     * @return question
     */
    @Override
    public Question create(Question question) {
        Question newQuestion = null;
        try {
            Preconditions.checkNotNull(question);
            newQuestion = questionRepository.save(question);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error question creating: " + JsonUtility.objectToJson(question), e);
        }
        return newQuestion;
    }
    
    /**
     * This method updates a question
     * @param question
     */
    @Override
    public void update(Question question) {
        try {
            Preconditions.checkNotNull(question);

            Question qUpdate = questionRepository.findById(question.getId()).orElse(null);

            Preconditions.checkNotNull(qUpdate);
           
            qUpdate.setText(question.getText());
            qUpdate.setSection(question.getSection());
            qUpdate.setType(question.getType());
            qUpdate.setWidgetType(question.getWidgetType());
            qUpdate.setEnabled(question.isEnabled());
            qUpdate.setRequired(question.isRequired());
            
            questionRepository.save(qUpdate);
            
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error question update: " + JsonUtility.objectToJson(question), e);
        }
    }
    
    /**
     * This method deletes a question by id
     * @param id: question id
     */
    @Override
    public void delete(Long id) {
        try {
            questionRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error question deletion with id: " + id, e);
        } 
    }

}

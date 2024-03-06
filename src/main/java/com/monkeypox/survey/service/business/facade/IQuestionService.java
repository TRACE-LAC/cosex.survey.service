package com.monkeypox.survey.service.business.facade;

import java.util.List;

import com.monkeypox.survey.service.model.question.Question;

public interface IQuestionService {

    List<Question> findAllQuestions();

    List<Question> findAllQuestionsWithAnswers();

    List<Question> findQuestionsWithAnswersBySection(String section);

    Question findById(Long id);

    Question create(Question question);

    void update(Question question);

    void delete(Long id);
}
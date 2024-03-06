package com.monkeypox.survey.service.business.facade;

import java.util.List;

import com.monkeypox.survey.service.model.answer.Answer;
import com.monkeypox.survey.service.model.question.Question;

public interface IAnswerService {

    List<Answer> findAllAnswers();

    Answer findById(Long id);

    List<Answer> findByQuestion(Question question);

    Answer create(Answer question);

    void update(Answer question);

    void delete(Long id);

}
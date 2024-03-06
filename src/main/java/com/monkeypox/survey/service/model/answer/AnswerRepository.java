package com.monkeypox.survey.service.model.answer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monkeypox.survey.service.model.question.Question;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
    List<Answer> findByQuestion(Question question);
}

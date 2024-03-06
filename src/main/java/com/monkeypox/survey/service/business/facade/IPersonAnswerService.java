package com.monkeypox.survey.service.business.facade;

import java.util.List;

import com.monkeypox.survey.service.model.person_answer.PersonAnswer;

public interface IPersonAnswerService {

    List<PersonAnswer> findAllPersonAnswers();

    PersonAnswer findById(Long id);

    PersonAnswer create(PersonAnswer question);

    List<PersonAnswer> createList(List<PersonAnswer> personAnswers);

    void update(PersonAnswer question);

    void delete(Long id);
}
package com.monkeypox.survey.service.business.facade;

import java.util.List;

import com.monkeypox.survey.service.model.survey.Survey;

public interface ISurveyService {

    List<Survey> findAllSurveys();

    Survey findById(Long id);

    Survey create(Survey survey);

    List<Survey> createSurveys(List<Survey> surveys);

    void update(Survey survey);

    void delete(Long id);

}
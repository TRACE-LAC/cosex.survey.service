package com.monkeypox.survey.service.business.facade;

import java.util.List;

import com.monkeypox.survey.service.model.answer.SurveyAnswer;

public interface ISurveyAnswerService {

    List<SurveyAnswer> findAllSurveyAnswers();

    SurveyAnswer findById(Long id);

    SurveyAnswer create(SurveyAnswer question);

    List<SurveyAnswer> createSurveyAnswers(List<SurveyAnswer> answers);

    void update(SurveyAnswer question);

    void delete(Long id);

}
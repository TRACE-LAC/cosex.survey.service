package com.monkeypox.survey.service.model.answer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "survey_answer")
public class SurveyAnswer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "date")
	private Date date;

	@Column(name = "survey_id")
    private Long survey_id;

	@Column(name = "question_id")
    private Long question_id;

	@Column(name = "answer_id")
    private Long answer_id;

	@Column(name = "text")
	private String text;
    
	@Column(name = "options")
	private String options;

}
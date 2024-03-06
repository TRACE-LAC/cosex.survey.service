package com.monkeypox.survey.service.model.person_answer;

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
@Table(name = "person_answer")
public class PersonAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @Column(name = "question_id", nullable = false)
	private Long questionId;

    @Column(name = "answer_id", nullable = false)
	private Long answerId;

    @Column(name = "person_id", nullable = false)
	private Long personId;

    @Column(name = "text", nullable = false, length = (800))
	private String text;

}
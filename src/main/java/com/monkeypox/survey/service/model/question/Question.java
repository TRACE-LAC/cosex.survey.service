package com.monkeypox.survey.service.model.question;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monkeypox.survey.service.model.answer.Answer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", nullable = false, length = (255))
	private String title;

	@Column(name = "text", nullable = false, length = (255))
	private String text;

    @Column(name = "section", nullable = false, length = (255))
	private String section;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "required", nullable = false)
	private boolean required;

	@Column(name = "type", nullable = false, length = (255))
	private String type;

	@Column(name = "widget_type", nullable = false, length = (255))
	private String widgetType;

	@Column(name = "definitions", nullable = true, length = (800))
	private String definitions;

	@Column(name = "parent")
	private Long parent;

	@OneToMany(mappedBy = "question")
	@JsonIgnore
	private List<Answer> answers;

	private String answersInJson;

}

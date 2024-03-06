package com.monkeypox.survey.service.security.secrets;

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
@Table(name = "secretsApp")
public class SecretsApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
    
    @Column(name = "name", length = (500))
	private String name;

	@Column(name = "value", length = (500))
	private String value;
    
}

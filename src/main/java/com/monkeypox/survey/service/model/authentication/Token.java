package com.monkeypox.survey.service.model.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {

    private boolean status;

    private String token;

    private Long id;

    private String errorMessage;
    
}

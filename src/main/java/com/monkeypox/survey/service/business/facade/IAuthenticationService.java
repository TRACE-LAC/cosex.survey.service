package com.monkeypox.survey.service.business.facade;

import com.monkeypox.survey.service.model.authentication.Token;

public interface IAuthenticationService {
    
    Token generateAuthentication() throws Exception;
}
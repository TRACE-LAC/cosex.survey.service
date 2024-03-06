package com.monkeypox.survey.service.business.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkeypox.survey.service.business.facade.IAuthenticationService;
import com.monkeypox.survey.service.model.authentication.Token;
import com.monkeypox.survey.service.security.SecurityUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private SecurityUtils securityUtils;

    private Long leftLimit = 1L;
    private Long rightLimit = 10L;

    @Override
    public Token generateAuthentication() throws Exception {
        Token token = new Token();
        Long randomId = -1L;
        Long randomIdPw = -1L;
        Date date = new Date();
        try {
            randomId = date.getTime() + (leftLimit + (long) (Math.random() * (rightLimit - leftLimit)));
            randomIdPw = date.getTime() + (leftLimit + (long) (Math.random() * (rightLimit - leftLimit)));
            token.setStatus(true);
            token.setId(randomId);
            token.setToken(securityUtils.getJWTToken(
                    randomId.toString(), randomIdPw.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error generate authentication", e);
            throw new Exception(e.getMessage());
        }

        return token;
    }
}